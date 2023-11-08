package lab_2;

import lab_2.transports.*;
import lab_2.exceptions.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws NoSuchModelNameException, DuplicateModelNameException, IOException, ClassNotFoundException {
        Transport transport = new Car("Aston Martin", 2);
        System.out.println("1-й Вывод");
        getAll(transport);

        FileOutputStream bytesOut = new FileOutputStream("Byte.bin");
        FileInputStream bytesIn = new FileInputStream("Byte.bin");

        FileWriter charsWriter = new FileWriter("Char.txt");
        FileReader charsReader = new FileReader("Char.txt");

        FileOutputStream serializationOut = new FileOutputStream("Serialization.bin");
        FileInputStream serializationIn = new FileInputStream("Serialization.bin");
        Transport deserializationTransport, byteTransport, charTransport, systemTransport;

        //байтовые потоки
        TransportStatic.outputTransport(transport, bytesOut);
        byteTransport = TransportStatic.inputTransport(bytesIn);
        bytesOut.close();
        bytesIn.close();
        System.out.println("2-й Вывод");
        getAll(byteTransport);

        //символьные потоки
        TransportStatic.writeTransport(transport, charsWriter);
        charTransport = TransportStatic.readTransport(charsReader);
        charsWriter.close();
        charsReader.close();
        System.out.println("3-й Вывод");
        getAll(charTransport);

        //ввод с консоли
        TransportStatic.writeTransport(transport, new OutputStreamWriter(System.out));
        systemTransport = TransportStatic.readTransport(new InputStreamReader(System.in));
        System.out.println("4-й Вывод");
        getAll(systemTransport);

        //сериализация
        ObjectOutputStream outputStream = new ObjectOutputStream(serializationOut);
        ObjectInputStream inputStream = new ObjectInputStream(serializationIn);
        outputStream.writeObject(transport);
        Object o = inputStream.readObject();
        deserializationTransport = o.getClass().getName().equals(Car.class.getName()) ? (Car) o : (Motorcycle) o;
        inputStream.close();
        outputStream.close();
        System.out.println("5-й Вывод");
        getAll(deserializationTransport);
    }

    private static void getAll(Transport transport) {
        System.out.println("Type: " + transport.getClass().getSimpleName());
        System.out.println("Mark: " + transport.getMark());
        System.out.println("Number of models: " + transport.getSize());
        TransportStatic.getAllModelNames(transport);
        TransportStatic.getAllModelPrices(transport);
        System.out.println("______________________________________________________");
    }
}