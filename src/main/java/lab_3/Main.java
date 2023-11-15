package lab_3;

import lab_3.threads.*;
import lab_3.transports.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private final static Transport transport = new Car("Aston Martin", 5);
    private final static TransportSynchronizer synchronizer = new TransportSynchronizer(transport);
    private final static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread prices = new PrintPrices(transport);
        Thread models = new PrintModels(transport);
        prices.setPriority(Thread.MAX_PRIORITY);
        models.setPriority(Thread.MIN_PRIORITY);
        models.start();
        prices.start();

        Thread syncModels = new Thread(new PrintModelsSync(synchronizer));
        Thread syncPrices = new Thread(new PrintPricesSync(synchronizer));
        syncModels.start();
        syncPrices.start();

        Thread lockModels = new Thread(new PrintModelsLock(transport, lock));
        Thread lockPrices = new Thread(new PrintPricesLock(transport, lock));
        lockModels.start();
        lockPrices.start();

        ExecutorService service = Executors.newFixedThreadPool(2);
        Transport[] transports = new Transport[]{
                new Car("CarMark_1", 2),
                new Car("CarMark_2", 3),
                new Motorcycle("BikeMark_1", 4),
                new Motorcycle("BikeMark_2", 5)};
        for (int i = 0; i < 4; i++) {
            service.submit(new PrintMark(transports[i]));
        }
        service.shutdown();

        ArrayBlockingQueue<Transport> queue = new ArrayBlockingQueue<>(1);
        String[] files = new String[]{"file_1.txt", "file_2.txt", "file_3.txt", "file_4.txt", "file_5.txt"};
        for (String file : files) {
            Thread thread = new Thread(new ReadTransport(file, queue));
            thread.start();
        }
        for (int i = 0; i != 5; i++) {
            System.out.println(queue.take().getMark());
        }
    }
}