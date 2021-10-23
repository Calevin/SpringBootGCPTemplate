package com.calevin.springbootgcptemplate.controllers;

import com.calevin.springbootgcptemplate.dtos.entityexample.CreateEntityExampleDTO;
import com.calevin.springbootgcptemplate.dtos.entityexample.EntityExampleConverterDTO;
import com.calevin.springbootgcptemplate.dtos.entityexample.GetEntityExampleDTO;
import com.calevin.springbootgcptemplate.entities.EntityExample;
import com.calevin.springbootgcptemplate.errors.NotFoundException;
import com.calevin.springbootgcptemplate.services.EntityExampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequiredArgsConstructor
public class EntityExampleController {

    private final EntityExampleService entityExampleService;

    private final EntityExampleConverterDTO converterDTO;

    @GetMapping("/entityExample")
    public ResponseEntity<List<GetEntityExampleDTO>> getAll() {
        log.info("getAll");
        List<EntityExample> entities = entityExampleService.findAll();

        if(entities.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(
                    entities.stream()
                    .map(converterDTO::converterToGetEntityExampleDTO)
                    .collect(Collectors.toList()));
        }
    }

    @GetMapping("/entityExample/{id}")
    public ResponseEntity<GetEntityExampleDTO> getOne(@PathVariable Long id) {
        log.info("getOne, id: {}", id);
        log.info("converterDTO: {}", converterDTO);
        return entityExampleService.findById(id)
                .map(converterDTO::converterToGetEntityExampleDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping("/entityExample")
    public ResponseEntity<EntityExample> newRecord(@RequestBody CreateEntityExampleDTO createEntityExampleDTO) {
        log.info("newRecord, createEntityExampleDTO: {}", createEntityExampleDTO);
        log.info("newRecord, converterDTO: {}", converterDTO);
        EntityExample newEntityExample = converterDTO.converterToEntityExample(createEntityExampleDTO);
        log.info("newRecord, newEntityExample: {}", newEntityExample);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityExampleService.save(newEntityExample));
    }

    @PutMapping("/entityExample/{id}")
    public ResponseEntity<EntityExample> editRecord(@RequestBody CreateEntityExampleDTO editEntityExampleDTO, @PathVariable Long id) {
        log.info("editRecord, id: {}", id);
        return entityExampleService
                .findById(id)
                .map(p -> {
                    EntityExample entityExample = converterDTO.converterToEntityExample(editEntityExampleDTO);
                    entityExample.setId(id);

                    return ResponseEntity.ok(entityExampleService.save(entityExample));
                })
                .orElseThrow(() -> new NotFoundException(id));
    }

    @DeleteMapping("/entityExample/{id}")
    public ResponseEntity<?> deleteRecord(@PathVariable Long id) {
        log.info("deleteRecord, id: {}", id);
        return entityExampleService
                .findById(id)
                .map( p -> {
                    entityExampleService.deleteById(id);
                    return ResponseEntity.status(HttpStatus.OK).build();
                })
                .orElse(ResponseEntity.noContent().build());
    }
}
