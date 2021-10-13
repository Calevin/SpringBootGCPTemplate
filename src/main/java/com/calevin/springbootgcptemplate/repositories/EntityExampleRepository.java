package com.calevin.springbootgcptemplate.repositories;

import com.calevin.springbootgcptemplate.entities.EntityExample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityExampleRepository extends JpaRepository<EntityExample, Long> {
}
