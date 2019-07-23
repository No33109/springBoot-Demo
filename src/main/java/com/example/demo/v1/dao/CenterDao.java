package com.example.demo.v1.dao;

import com.example.demo.v1.domain.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CenterDao extends JpaRepository<Center,Long>, JpaSpecificationExecutor<Center> {
}
