package lab_3.threads;

import lab_3.transports.Transport;
import lab_3.transports.TransportStatic;

import java.util.concurrent.locks.Lock;

public class PrintPricesLock implements Runnable {
    private final Transport transport;
    private final Lock lock;

    public PrintPricesLock(Transport transport, Lock lock) {
        this.transport = transport;
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            TransportStatic.getAllModelPrices(transport);
        } finally {
            lock.unlock();
        }
    }
}