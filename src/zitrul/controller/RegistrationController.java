package zitrul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zitrul.domain.Hello;
import zitrul.service.HelloService;

@RestController
@RequestMapping("registration/v1")
public class RegistrationController {

    private final HelloService service;

    @Autowired
    public RegistrationController(HelloService service) {
        this.service = service;
    }

    @GetMapping
    public Hello getReg(String login, String password, String img, String st1, String st2) {
        return service.getReg(login, password, img, st1, st2);
    }
}