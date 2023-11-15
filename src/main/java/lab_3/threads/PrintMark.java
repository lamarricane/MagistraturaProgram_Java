package lab_3.threads;

import lab_3.transports.Transport;

public class PrintMark implements Runnable {
    private final Transport transport;

    public PrintMark(Transport transport) {
        this.transport = transport;
    }

    @Override
    public void run() {
        System.out.println("Mark: " + transport.getMark());
    }
}