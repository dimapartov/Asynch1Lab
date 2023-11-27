package com.example.asynchfirstlab;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private static final int MAX_CAPACITY = 5;
    private Queue<Integer> buffer = new LinkedList<>();
    private Label bufferLabel;

    public Buffer() {
        bufferLabel = new Label();
        updateBufferLabel();
    }

    public Scene getScene() {
        VBox vbox = new VBox(bufferLabel);
        return new Scene(vbox, 300, 100);
    }

    public synchronized void addToBuffer(int item) {
        while (buffer.size() == MAX_CAPACITY) {
            try {
                Platform.runLater(() -> bufferLabel.setText("Буфер заполнен. Производитель ждет..."));
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        buffer.add(item);
        updateBufferLabel();
        notifyAll();
    }

    public synchronized int removeFromBuffer() {
        while (buffer.isEmpty()) {
            try {
                Platform.runLater(() -> bufferLabel.setText("Буфер пуст. Потребитель ждет..."));
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int item = buffer.poll();
        updateBufferLabel();
        notifyAll();
        return item;
    }

    private void updateBufferLabel() {
        Platform.runLater(() -> {
            StringBuilder bufferContent = new StringBuilder("Буфер: [");
            for (Integer item : buffer) {
                bufferContent.append(item).append(", ");
            }
            if (!buffer.isEmpty()) {
                bufferContent.delete(bufferContent.length() - 2, bufferContent.length());
            }
            bufferContent.append("]");
            bufferLabel.setText(bufferContent.toString());
        });
    }
}