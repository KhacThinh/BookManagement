package com.example.AuthorManagement.repositories;

import com.example.AuthorManagement.model.entities.ZipCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipCodeRepository extends JpaRepository<ZipCode, Long> {
}
