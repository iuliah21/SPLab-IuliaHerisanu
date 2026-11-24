package ro.uvt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.uvt.difexamples.ClientComponent;

@RestController
public class HelloController {
    private final ClientComponent clientComponent;
    private final ClientComponent secondClientComponent;

    public HelloController(ClientComponent clientComponent,
                           ClientComponent secondClientComponent) {
        this.clientComponent = clientComponent;
        this.secondClientComponent = secondClientComponent;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello from ClientComponent = " + clientComponent;
    }

    @GetMapping("/hello")
    public String helloSpring() {
        return "Hello from Spring Boot";
    }

    @GetMapping("/compare")
    public String compare() {
        return "Same instance: " +
                (clientComponent == secondClientComponent);
    }
}