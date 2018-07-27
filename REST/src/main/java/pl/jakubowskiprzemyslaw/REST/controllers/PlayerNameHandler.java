package pl.jakubowskiprzemyslaw.REST.controllers;

import org.springframework.stereotype.Component;

@Component
public class PlayerNameHandler {

    private String name;

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}
