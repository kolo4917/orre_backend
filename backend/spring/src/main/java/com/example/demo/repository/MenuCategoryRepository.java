package com.example.demo.repository;

import com.example.demo.model.DataBase.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Integer> {

    MenuCategory findByStoreCode(Integer storeCode);
}


