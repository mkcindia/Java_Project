package utils;

import java.util.LinkedList;
import java.util.List;

public class ProducerConsumerExample {
    private final List<Integer> taskQueue = new LinkedList<>();
    private final int MAX_CAPACITY = 5;

    public static void main(String[] args) {
        ProducerConsumerExample example = new ProducerConsumerExample();
        Thread producer = new Thread(new Producer(example));
        Thread consumer = new Thread(new Consumer(example));

        producer.start();
        consumer.start();
    }

    public void produce(int i) throws InterruptedException {
        synchronized (this) {
            while (taskQueue.size() == MAX_CAPACITY) {
                wait();
            }
            taskQueue.add(i);
            System.out.println("Produced: " + i);
            notify();
        }
    }

    public void consume() throws InterruptedException {
        synchronized (this) {
            while (taskQueue.isEmpty()) {
                wait();
            }
            int i = taskQueue.remove(0);
            System.out.println("Consumed: " + i);
            notify();
        }
    }
}

class Producer implements Runnable {
    private final ProducerConsumerExample example;

    public Producer(ProducerConsumerExample example) {
        this.example = example;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            try {
                example.produce(i++);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private final ProducerConsumerExample example;

    public Consumer(ProducerConsumerExample example) {
        this.example = example;
    }

    @Override
    public void run() {
        while (true) {
            try {
                example.consume();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
