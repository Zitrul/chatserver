package zitrul.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zitrul.domain.Hello;
import zitrul.service.HelloService;

@RestController
@RequestMapping("hello/v1")
public class HelloController {

    private final HelloService service;

    @Autowired
    public  HelloController(HelloService service){
        this.service = service;
    }
    @GetMapping
    public Hello getHello(@RequestParam(defaultValue = "world") String name, String password){
        return service.getHello(name,password);
    }
}
