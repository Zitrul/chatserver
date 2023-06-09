package zitrul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        String password = "myPassword";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        SpringApplication.run(Main.class);
        System.out.println(hashedPassword);
        String userInputPassword = "myPassword";
        if(passwordEncoder.matches(userInputPassword, hashedPassword)) {
            System.out.println("OK");
        } else {
            System.out.println("ERERERE");
        }
    }
}