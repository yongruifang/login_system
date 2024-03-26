package org.example.Controller;

import org.example.Controller.Api.DemoApi;
import org.example.Service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DemoController implements DemoApi {
    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private DemoService demoService;
    @GetMapping("/hello")
    public String index() {
        return "hello, Greeting from Demo application";
    }
    @GetMapping("/greet")
    public DemoService.Greeting greeting(@RequestParam(value="name", defaultValue="World") String name){
        return new DemoService.Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
