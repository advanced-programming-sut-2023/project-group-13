package org.example;

import controller.ControllerControllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        ControllerControllers controllerControllers = new ControllerControllers();
        controllerControllers.run();
    }


}