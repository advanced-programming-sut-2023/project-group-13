package org.example;

import controller.ControllerControllers;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        ControllerControllers controllerControllers = new ControllerControllers();
        controllerControllers.run();
    }
}