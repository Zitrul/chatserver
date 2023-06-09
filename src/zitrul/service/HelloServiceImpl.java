package zitrul.service;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zitrul.db.SQLiteJDBC;
import zitrul.domain.Hello;
import zitrul.domain.LH;

import java.util.ArrayList;
import java.util.List;

@Service
public class HelloServiceImpl implements HelloService{

    
    @Override
    public Hello getHello(String login, String password) {
        System.out.println(login);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("Mypass"+ SQLiteJDBC.check_password(login) + password);
        if(passwordEncoder.matches(password, SQLiteJDBC.check_password(login))) {
            System.out.println("PASS iS CORRECT");
            int id = SQLiteJDBC.getid(login);
            if(id >=0){
                return new Hello(Integer.toString(id));
            }
            return new Hello("WRONG AUTH");
        }

        return new Hello("WRONG AUTH");
    }
    @Override
    public Hello getReg(String login,String password,String img,String st1, String st2) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        String answer = SQLiteJDBC.write(SQLiteJDBC.get_lastid()+1,login, hashedPassword, img,st1,st2);

        return new Hello(answer);
    }
}
