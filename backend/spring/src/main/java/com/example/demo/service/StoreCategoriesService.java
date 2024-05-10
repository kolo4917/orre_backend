package com.example.demo.service;

import com.example.demo.DTO.ToServer.StoreMenuCategoryRequest;
import com.example.demo.model.DataBase.MenuCategory;
import com.example.demo.repository.MenuCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;

@Service
public class StoreCategoriesService {

    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    @Transactional
    public String updateMenuCategory(StoreMenuCategoryRequest request) {
        MenuCategory menuCategory = menuCategoryRepository.findByStoreCode(request.getStoreCode());
        if (menuCategory != null) {
            try {
                String fieldName = request.getSingleMenuCode();
                Field field = MenuCategory.class.getDeclaredField(fieldName); // 해당 필드 접근
                field.setAccessible(true);
                field.set(menuCategory, request.getMenuCategory()); // 필드 값을 업데이트
                menuCategoryRepository.save(menuCategory);
                return "200";
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return "6102";
            }
        }
        return "6101";
    }
}
