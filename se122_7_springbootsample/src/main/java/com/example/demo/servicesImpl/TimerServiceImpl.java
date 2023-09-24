package com.example.demo.servicesImpl;

import com.example.demo.services.TimerService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("session")
public class TimerServiceImpl implements TimerService {
    private long startTime = 0;
    private boolean isTiming = false;

    public void start(){
        if(!isTiming){
            startTime = System.currentTimeMillis();
            isTiming = true;
            System.out.println("开始计时");
        }
    }

    public long stop(){
        if(isTiming){
            isTiming = false;
            System.out.println(System.currentTimeMillis() - startTime);
            return System.currentTimeMillis() - startTime;
        }
        return 0;
    }
}
