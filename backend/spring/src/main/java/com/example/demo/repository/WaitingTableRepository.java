package com.example.demo.repository;

import com.example.demo.model.WaitingTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingTableRepository extends JpaRepository<WaitingTable, Long> {

}
