package com.gd.datachx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.gd.datachx.mapper")
@EnableScheduling
public class DataChxApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication application = new SpringApplication(DataChxApplication.class );
        application.setApplicationContextClass( AnnotationConfigApplicationContext.class );
        application.run( args );

//        String path = System.getProperty("classpath:logback-spring.xml");
//        PropertyConfigurator.configure(path);

        CountDownLatch latch = new CountDownLatch( 1 );
        latch.await();
    }

}
