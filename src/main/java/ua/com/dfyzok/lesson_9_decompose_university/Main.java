package ua.com.dfyzok.lesson_9_decompose_university;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
@ComponentScan(basePackages = "ua.com.dfyzok.lesson_9_decompose_university")
public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            SpringApplication.run(Main.class, args);
            System.out.println("33333333");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
