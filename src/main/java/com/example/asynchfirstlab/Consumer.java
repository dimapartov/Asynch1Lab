package com.example.asynchfirstlab;

public class Consumer extends Thread {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            int item = buffer.removeFromBuffer();
            try {
                Thread.sleep((long) (Math.random() * 15000)); // Подождать случайное время
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}