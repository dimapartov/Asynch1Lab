package com.example.asynchfirstlab;

import java.util.Random;

public class Producer extends Thread {
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            int item = new Random().nextInt(100); // Создать случайный товар
            buffer.addToBuffer(item);
            try {
                Thread.sleep((long) (Math.random() * 15000)); // Подождать случайное время
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}