package br.com.eduardoAssisSilva.controllers;

import br.com.eduardoAssisSilva.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong coutner = new AtomicLong();

    //http://localhost:8080/greeting?name=eduardo
    @RequestMapping("/greeting")
    public Greeting greeting(
            @RequestParam(value = "name", defaultValue = "World")
            String name){
        return new Greeting(coutner.incrementAndGet(), String.format(template, name));
    }
}
