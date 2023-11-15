package lab_3.threads;

import lab_3.transports.Transport;

public class TransportSynchronizer {
    private final Transport v;
    private volatile int current = 0;
    private final Object lock = new Object();
    private boolean set = false;

    public TransportSynchronizer(Transport v) {
        this.v = v;
    }

    public void printPrice() throws InterruptedException {
        double val;
        synchronized (lock) {
            double[] p = v.getAllModelPrices();
            if (!canPrintPrice()) throw new InterruptedException();
            while (!set)
                lock.wait();
            val = p[current++];
            System.out.println("Print price: " + val);
            set = false;
            lock.notifyAll();
        }
    }

    public void printModel() throws InterruptedException {
        synchronized (lock) {
            String[] s = v.getAllModelNames();
            if (!canPrintModel()) throw new InterruptedException();
            while (set)
                lock.wait();
            System.out.println("Print model: " + s[current]);
            set = true;
            lock.notifyAll();
        }
    }

    public boolean canPrintPrice() {
        return current < v.getSize();
    }

    public boolean canPrintModel() {
        return (!set && current < v.getSize()) || (set && current < v.getSize() - 1);
    }
}
