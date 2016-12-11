package org.cs.contactus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	
	@Bean(name="emailWorkerExecutor")
	public TaskExecutor taskExecutor() {
	    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	    taskExecutor.setMaxPoolSize(10);
	    taskExecutor.setQueueCapacity(500);
	    taskExecutor.afterPropertiesSet();
	    return taskExecutor;
	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
