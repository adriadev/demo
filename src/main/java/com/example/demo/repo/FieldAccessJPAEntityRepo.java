package com.example.demo.repo;

import com.example.demo.entity.FieldAccessJPAEntity;
import com.example.demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldAccessJPAEntityRepo extends JpaRepository<FieldAccessJPAEntity, Long> {
}
