package com.example.demo.edu.Client;

import org.springframework.stereotype.Component;

/**
 * @author cc
 * @date 2023年02月10日 15:05
 */
@Component
public class OrderClientImpl implements OrderClient {
    @Override
    public boolean isBuy(String courseId, String memberId) {
        return false;
    }
}
