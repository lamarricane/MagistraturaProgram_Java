package lab_3.threads;

public class PrintPricesSync implements Runnable {
    private final TransportSynchronizer synchronizer;

    public PrintPricesSync(TransportSynchronizer synchronizer) {
        this.synchronizer = synchronizer;
    }

    @Override
    public void run() {
        try {
            while (synchronizer.canPrintPrice()) {
                synchronizer.printPrice();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}