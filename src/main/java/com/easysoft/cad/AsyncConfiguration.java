package com.easysoft.cad;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfiguration implements AsyncConfigurer  {

	// 声明一个线程池(并指定线程池的名字)
	@Bean("taskExecutor")
	public ThreadPoolTaskExecutor taskExecutor() {

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		// 核心线程数5：线程池创建时候初始化的线程数
		executor.setCorePoolSize(5);
		// 最大线程数5：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
		executor.setMaxPoolSize(5);
		// 缓冲队列500：用来缓冲执行任务的队列
		executor.setQueueCapacity(500);
		// 允许线程的空闲时间60秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
		executor.setKeepAliveSeconds(60);
		// 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
		executor.setThreadNamePrefix("Easysoft-cad-Async-");
		
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		
        executor.setWaitForTasksToCompleteOnShutdown(true);
        
        executor.setAwaitTerminationSeconds(60);
		
		executor.initialize();
		
		return executor;
	}
	
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }

}
