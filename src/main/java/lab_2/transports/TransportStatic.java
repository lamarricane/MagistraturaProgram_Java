package lab_2.transports;

import lab_2.exceptions.*;

import java.io.*;

public class TransportStatic {

    public static void getAllModelPrices(Transport transport) {
        for (double price : transport.getAllModelPrices()
        ) {
            System.out.println("Prices:");
            System.out.println(price);
        }
    }

    public static void getAllModelNames(Transport transport) {
        for (String name : transport.getAllModelNames()
        ) {
            System.out.println("Model's names:");
            System.out.println(name);
        }
    }

    public static double calcMidPrice(Transport transport) {
        double sum = 0;
        for (double p : transport.getAllModelPrices()) {
            System.out.println(sum);
            sum += p;
        }
        return sum / transport.getAllModelPrices().length;
    }

    public static void outputTransport(Transport transport, OutputStream out) throws IOException, NoSuchModelNameException {
        DataOutputStream stream = new DataOutputStream(out);
        stream.writeInt(transport.getClass().getName().getBytes().length);
        writeBytes(stream, transport.getClass().getName().getBytes());
        stream.writeInt(transport.getMark().getBytes().length);
        writeBytes(stream, transport.getMark().getBytes());
        stream.writeInt(transport.getSize());
        for (int i = 0; i < transport.getSize(); i++) {
            stream.writeInt(transport.getAllModelNames()[i].getBytes().length);
            writeBytes(stream, transport.getAllModelNames()[i].getBytes());
            stream.writeDouble(transport.getModelPriceByName(transport.getAllModelNames()[i]));
        }
    }

    public static Transport inputTransport(InputStream input) throws IOException, DuplicateModelNameException {
        DataInputStream stream = new DataInputStream(input);
        String type = new String(readBytes(stream, stream.readInt()));
        String mark = new String(readBytes(stream, stream.readInt()));
        Transport transport = (type.equals("Car") ? new Car(mark, 0) : new Motorcycle(mark, 0));
        int size = stream.readInt();
        for (int i = 0; i < size; i++) {
            transport.addModel(new String(readBytes(stream, stream.readInt())), stream.readDouble());
        }
        return transport;
    }

    public static void writeTransport(Transport transport, Writer out) {
        PrintWriter writer = new PrintWriter(out);
        writer.println(transport.getClass().getSimpleName());
        writer.println(transport.getMark());
        writer.println(transport.getSize());
        String[] names = transport.getAllModelNames();
        double[] prices = transport.getAllModelPrices();

        for (int i = 0; i < transport.getSize(); i++) {
            writer.println(names[i]);
            writer.println(prices[i]);
        }
        writer.flush();
    }

    public static Transport readTransport(Reader in) throws IOException, DuplicateModelNameException {
        BufferedReader reader = new BufferedReader(in);

        String type = reader.readLine();
        String mark = reader.readLine();

        int size = Integer.parseInt(reader.readLine());
        Transport transport = (type.equals("Car") ? new Car(mark, 0) : new Motorcycle(mark, 0));


        for (int i = 0; i < size; i++) {
            transport.addModel(reader.readLine(), Double.parseDouble(reader.readLine()));
        }
        // reader.close();
        return transport;
    }

    public static void writeBytes(DataOutputStream stream, byte[] array) throws IOException {
        for (byte b : array) {
            stream.writeByte(b);
        }
    }

    public static byte[] readBytes(DataInputStream stream, int length) throws IOException {
        byte[] array = new byte[length];
        for (int i = 0; i < length; i++) {
            array[i] = stream.readByte();
        }
        return array;
    }
}