package com.zpwq.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskDemo {
	
	//@Scheduled(fixedRate = 3000)
	public void doSomething(){
		System.out.println("定时任务："+System.currentTimeMillis());
	}

}
