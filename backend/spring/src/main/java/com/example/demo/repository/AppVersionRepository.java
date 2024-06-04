package com.example.demo.repository;

import com.example.demo.model.DataBase.AppVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppVersionRepository extends JpaRepository<AppVersion, Integer> {
    AppVersion findByAppCode(Integer appCode);


}
