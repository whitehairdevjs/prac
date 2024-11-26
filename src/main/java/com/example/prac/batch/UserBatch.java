package com.example.prac.batch;

import com.example.prac.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserBatch {
    @Autowired
    private UserService userService;

    public void processUserTest() throws Exception {
        log.info("유저 테스트 배치...");
        try {
            userService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("유저 테스트중 에러 발생...");
        }
    }
}
