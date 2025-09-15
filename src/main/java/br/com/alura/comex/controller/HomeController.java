package br.com.alura.comex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "A aplicação está funcionando";
    }

    @GetMapping("/status")
    public String status() {
        return "✅ Porta 8091";
    }
}