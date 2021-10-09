package com.calevin.springbootgcptemplate.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "entity_example")
@Data
@NoArgsConstructor
public class EntityExample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn( name = "category_example_id")
    private CategoryExample category;
}
