package com.calevin.springbootgcptemplate.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "entity_example")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
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
