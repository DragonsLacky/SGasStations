package mk.finki.dians.sawebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ServletComponentScan
@EnableMongoRepositories
public class SaWebAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaWebAppApplication.class, args);
    }
    
}
