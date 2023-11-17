package lab_4.transports;

import lab_4.exceptions.DuplicateModelNameException;
import lab_4.exceptions.ModelPriceOutOfBoundsException;
import lab_4.exceptions.NoSuchModelNameException;


import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Motorcycle implements Transport {

    private static class Model implements Serializable { //через двусвязный список
        @Serial
        private static final long serialVersionUID = 2199499766118893431L;
        String name;
        double price;
        Model prev = null;
        Model next = null;

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    public int size;
    public static String mark;
    private final Model head = new Model("Name_0", 6800);
    private transient long lastModified;

    public Motorcycle(String mark, int size) {
        Motorcycle.mark = mark;
        this.size = size;
        head.prev = head;
        head.next = head;
        for (int i = 0; i < size; i++) {
            Model current = new Model("Name_" + i, 567);
            current.prev = head.prev;
            current.next = head;
            head.prev.next = current;
            head.prev = current;
        }
    }

    {
        lastModified = System.currentTimeMillis();
    }

    public String[] getAllModelNames() {
        Model current = head;
        String[] names = new String[size];
        names[0] = current.name;
        for (int i = 1; i < size; i++) {
            current = current.next;
            names[i] = current.name;
        }
        return names;
    }

    public double[] getAllModelPrices() {
        Model current = head;
        double[] prices = new double[size];
        prices[0] = current.price;
        for (int i = 1; i < size; i++) {
            current = current.next;
            prices[i] = current.price;
        }
        return prices;
    }

    public double getModelPriceByName(String name) throws NoSuchModelNameException {
        Model current = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.name, name)) {
                return current.price;
            }
            current = current.next;
        }
        throw new NoSuchModelNameException("Model with name: " + name + " doesn't exist");
    }

    public void modifyModelName(String name, String new_name) throws NoSuchModelNameException, DuplicateModelNameException {
        Model current = head.next;
        while (current == head) {
            if (Objects.equals(current.name, new_name)) {
                throw new DuplicateModelNameException("Name " + new_name + " has already exist");
            }
            current = current.next;
        }
        boolean term = false;
        Model current1 = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current1.name, name)) {
                current1.name = new_name;
                term = true;
                break;
            }
            current1 = current1.next;
        }
        if (!term) {
            throw new NoSuchModelNameException("Model with name: " + name + " doesn't exist");
        }
        lastModified = System.currentTimeMillis();
    }

    public void modifyModelPriceByName(String name, double new_price) throws NoSuchModelNameException {
        if (new_price < 0) {
            throw new ModelPriceOutOfBoundsException("Price can't be < 0 ");
        }
        boolean term = false;
        Model current1 = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current1.name, name)) {
                current1.price = new_price;
                term = true;
                break;
            }
            current1 = current1.next;
        }
        if (!term) {
            throw new NoSuchModelNameException("Model with name: " + name + " doesn't exist");
        }
        lastModified = System.currentTimeMillis();
    }

    public void addModel(String name, double price) throws DuplicateModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("Price can't be < 0 ");
        }
        Model next = head.next;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(next.name, name)) {
                throw new DuplicateModelNameException("Name " + name + " has already exist");
            }
            next = next.next;
        }

        Model current1 = new Model(name, price);
        current1.prev = head.prev;
        current1.next = head;
        head.prev.prev.next = current1;
        head.prev = current1;
        size++;
        lastModified = System.currentTimeMillis();
    }

    public void removeModel(String name) throws NoSuchModelNameException {
        Model current = head.next;
        boolean term = false;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.name, name)) {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                term = true;
                break;
            }
            current = current.next;
        }
        if (!term) {
            throw new NoSuchModelNameException("Model with name: " + name + " doesn't exist");
        }
        size--;
        lastModified = System.currentTimeMillis();

    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        Motorcycle.mark = mark;
    }

    public int getSize() {
        return size;
    }

    public long GetLastModified() {
        return lastModified;
    }
}
