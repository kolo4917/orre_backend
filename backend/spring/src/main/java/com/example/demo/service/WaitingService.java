package com.example.demo.service;

import com.example.demo.model.WaitingTable;
import com.example.demo.repository.WaitingTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaitingService {

    @Autowired
    private WaitingTableRepository waitingTableRepository;

    public WaitingTable registerWaiting(WaitingTable waitingTable) {
        // 웨이팅 정보 등록 로직
        return waitingTableRepository.save(waitingTable);
    }

    // 다른 비즈니스 로직 구현
}
