package lab_3.threads;

import lab_3.transports.Car;
import lab_3.transports.Transport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

public class ReadTransport implements Runnable {
    private final String filename;
    private final ArrayBlockingQueue<Transport> queue;

    public ReadTransport(String filename, ArrayBlockingQueue<Transport> queue) {
        this.filename = filename;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String mark = reader.readLine();
            Transport transport = new Car(mark, 0);
            queue.put(transport);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}