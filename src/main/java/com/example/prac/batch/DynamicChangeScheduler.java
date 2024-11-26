package com.example.prac.batch;

import com.example.prac.entity.Batch;
import com.example.prac.util.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DynamicChangeScheduler {
    private ThreadPoolTaskScheduler scheduler;

    // 스케줄러 시작
    public void startScheduler(List<Batch> batchList) throws Exception {
        log.info("스케줄러 시작...");
        scheduler= new ThreadPoolTaskScheduler();
        int threadCount = Runtime.getRuntime().availableProcessors(); // 컴퓨터의 스레드 수를 가져옴
        scheduler.setPoolSize(threadCount + 1); // 스레드 수 + 1. 미지정시 default는 1이다.
        scheduler.initialize();

        if (batchList.size() > 0) {
            for (int i = 0; i < batchList.size(); i++) {
                Batch batch = batchList.get(i);
                Object service = BeanUtils.getBean(batch.getServiceName());
                Runnable runnable = getRunnable(service, batch.getMethodName());
                Trigger cronTrigger = getTrigger(batch.getCron());
                scheduler.schedule(runnable, cronTrigger);
            }
        }
    }

    private Runnable getRunnable(Object service, String methodName) {
        return () -> {
            try {
                service.getClass().getDeclaredMethod(methodName).invoke(service);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    private Trigger getTrigger(String cron) { return new CronTrigger(cron); }

    // 스케줄러 종료
    public void stopScheduler() {
        log.info("스케줄러 종료...");
        scheduler.shutdown();
    }
}
