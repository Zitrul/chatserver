package zitrul.service;


import zitrul.domain.Hello;
import zitrul.domain.LH;

import java.util.List;

public interface HelloService {
    Hello getHello(String name, String password);
    Hello getReg(String login,String password,String img,  String st1, String st2);
}
