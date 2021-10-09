package com.calevin.springbootgcptemplate.services;

import com.calevin.springbootgcptemplate.entities.EntityExample;
import com.calevin.springbootgcptemplate.repositories.EntityExampleRepository;
import org.springframework.stereotype.Service;

@Service
public class EntityExampleService extends BaseService<EntityExample, Long, EntityExampleRepository> {
}
