package main.rest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyCustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @GetMapping(value=PATH)
    public String error() {
        return "invalid URL";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
