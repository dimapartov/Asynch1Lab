package com.example.asynchfirstlab;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Buffer buffer = new Buffer();

        Producer producer1 = new Producer(buffer);
        Producer producer2 = new Producer(buffer);
        Consumer consumer1 = new Consumer(buffer);
//        Consumer consumer2 = new Consumer(buffer);

        producer1.start();
        producer2.start();
        consumer1.start();
//        consumer2.start();

        primaryStage.setTitle("Производители и потребители");
        primaryStage.setScene(buffer.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}