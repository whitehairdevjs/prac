package com.example.prac.entity;

// batch_list 테이블 DTO

import lombok.Data;

@Data
public class Batch {
    private String serviceName;

    private String methodName;

    private String profileType;

    private String cron;

    private String useYn;

    private String description;
}
