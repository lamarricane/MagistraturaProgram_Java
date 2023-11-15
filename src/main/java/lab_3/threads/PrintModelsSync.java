package lab_3.threads;

public class PrintModelsSync implements Runnable {
    private final TransportSynchronizer synchronizer;

    public PrintModelsSync(TransportSynchronizer synchronizer) {
        this.synchronizer = synchronizer;
    }

    @Override
    public void run() {
        try {
            while (synchronizer.canPrintModel()) {
                synchronizer.printModel();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}