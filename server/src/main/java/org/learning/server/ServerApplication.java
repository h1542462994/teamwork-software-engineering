package org.learning.server;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 回退版本
@SpringBootApplication
public class ServerApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //dataBaseSeeder.insertData();
    }
}
