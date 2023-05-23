package com.example.demo.domain.farm.repository;

import com.example.demo.domain.farm.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<Farm,Long> {

}
