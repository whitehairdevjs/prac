package com.example.prac.service;

import com.example.prac.batch.DynamicChangeScheduler;
import com.example.prac.entity.Batch;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class CommandService  implements CommandLineRunner {
    @Autowired
    private DynamicChangeScheduler dynamicChangeScheduler;

    @Autowired
    private BatchService batchService;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Override
    public void run(String... args) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("profile", activeProfile);
        List<Batch> batchList = batchService.selectBatchList(map);
        dynamicChangeScheduler.startScheduler(batchList);
    }

    @PreDestroy
    public void batchDestroy() throws Exception {
        dynamicChangeScheduler.stopScheduler();
    }
}
