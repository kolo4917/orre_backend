package com.example.demo.repository;

import com.example.demo.model.DataBase.DankookMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DankookMenuRepository extends JpaRepository<DankookMenu, DankookMenu> {
    boolean existsByDateAndRestaurant(String date, String restaurant);
    DankookMenu findByDateAndRestaurant(String date, String restaurant);


}
