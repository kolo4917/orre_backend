package com.example.demo.service;

import com.example.demo.DTO.ToServer.StoreTimeModifyRequest;
import com.example.demo.model.DataBase.Store;
import com.example.demo.DTO.ToClient.StatusResponse;
import com.example.demo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.sql.Time;

@Service
public class StoreTimeModifyService {

    @Autowired
    private StoreRepository storeRepository;

    public StatusResponse updateStoreTimes(StoreTimeModifyRequest request) {
        Store store = storeRepository.findByStoreCode(request.getStoreCode());

        if (store != null) {
            if (request.getNewOpeningTime() != null) {
                store.setOpeningTime(request.getNewOpeningTime().toLocalTime());
            }
            if (request.getNewClosingTime() != null) {
                store.setClosingTime(request.getNewClosingTime().toLocalTime());
            }
            if (request.getNewLastOrderTime() != null) {
                store.setLastOrderTime(request.getNewLastOrderTime().toLocalTime());
            }
            if (request.getNewStartBreakTime() != null) {
                store.setStartBreakTime(request.getNewStartBreakTime().toLocalTime());
            }
            if (request.getNewEndBreakTime() != null) {
                store.setEndBreakTime(request.getNewEndBreakTime().toLocalTime());
            }

            storeRepository.save(store);
            return new StatusResponse("200");
        } else {
            return new StatusResponse("6601"); // Store not found
        }
    }
}
