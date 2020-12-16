package org.learning.server;

import org.learning.server.seeder.DataBaseSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication implements ApplicationRunner {

    DataBaseSeeder dataBaseSeeder;

    @Autowired
    public void setDataBaseSeeder(DataBaseSeeder dataBaseSeeder) {
        this.dataBaseSeeder = dataBaseSeeder;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dataBaseSeeder.insertData();
    }
}
