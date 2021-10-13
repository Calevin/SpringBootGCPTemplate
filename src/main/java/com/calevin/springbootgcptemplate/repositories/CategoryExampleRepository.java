package com.calevin.springbootgcptemplate.repositories;

import com.calevin.springbootgcptemplate.entities.CategoryExample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryExampleRepository extends JpaRepository<CategoryExample, Long> {
}
