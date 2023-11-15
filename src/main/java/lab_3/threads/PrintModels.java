package lab_3.threads;

import lab_3.transports.Transport;
import lab_3.transports.TransportStatic;

public class PrintModels extends Thread {
    private final Transport transport;

    public PrintModels(Transport transport) {
        this.transport = transport;
    }

    @Override
    public void run() {
        TransportStatic.getAllModelNames(transport);
    }
}