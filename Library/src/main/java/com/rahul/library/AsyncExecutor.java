package com.rahul.library;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class AsyncExecutor {

	private static final Logger logger = LoggerFactory.getLogger(AsyncExecutor.class);

	@Bean(name = "uploadExecutor")
	public ThreadPoolTaskExecutor uploadExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setThreadNamePrefix("Upload_Executor-");// Threads prefix that are in the pool
		threadPoolTaskExecutor.setCorePoolSize(3);// default is 1
		threadPoolTaskExecutor.setMaxPoolSize(3);// default is Integer.MAX_VALUE
		threadPoolTaskExecutor.setQueueCapacity(600);// default is Integer.MAX_VALUE
		threadPoolTaskExecutor.afterPropertiesSet();// calls initialize() to set up executorService
		logger.info("ThreadPoolTaskExecutor set");
		return threadPoolTaskExecutor;
	}
}
