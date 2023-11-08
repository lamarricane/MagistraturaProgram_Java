package lab_2.transports;

import lab_2.exceptions.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Car implements Transport {
    public String mark;
    public Model[] models;

    public Car(String mark, int size) {  //через массив
        this.mark = mark;
        models = new Model[size];
        for (int i = 0; i < size; i++) {
            models[i] = new Model("Name_" + i, 123);
        }
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    private static class Model implements Serializable {
        @Serial
        private static final long serialVersionUID = 4815229642325525123L;
        String name;
        double price;

        private Model(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    public String[] getAllModelNames() {
        String[] AllModelNames = new String[models.length];
        for (int i = 0; i < models.length; i++) {
            AllModelNames[i] = models[i].name;
        }
        return AllModelNames;
    }

    public double getModelPriceByName(String name) throws NoSuchModelNameException {
        for (Model model : models) {
            if (Objects.equals(name, model.name))
                return model.price;
        }
        throw new NoSuchModelNameException("Model with name: " + name + " doesn't exist");
    }

    public void modifyModelName(String name, String new_name) throws DuplicateModelNameException, NoSuchModelNameException {
        for (Model model : models) {
            if (Objects.equals(new_name, model.name))
                throw new DuplicateModelNameException("Name " + new_name + " has already exist");
        }
        boolean term = false;
        for (Model model : models) {
            if (Objects.equals(name, model.name))
                model.name = new_name;
            term = true;
            break;
        }
        if (!term)
            throw new NoSuchModelNameException("Model with name: " + name + " doesn't exist");
    }

    public void modifyModelPriceByName(String name, double new_price) throws NoSuchModelNameException {
        if (new_price < 0) {
            throw new ModelPriceOutOfBoundsException("Price can't be < 0 ");
        }
        boolean term = false;
        for (Model model : models) {
            if (Objects.equals(name, model.name))
                model.price = new_price;
            term = true;
            break;
        }
        if (!term)
            throw new NoSuchModelNameException("Model with name: " + name + " doesn't exist");
    }

    public double[] getAllModelPrices() {
        double[] AllModelPrices = new double[models.length];
        for (int i = 0; i < models.length; i++) {
            AllModelPrices[i] = models[i].price;
        }
        return AllModelPrices;
    }

    public void addModel(String name, double price) throws DuplicateModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("Price can't be < 0 ");
        }
        for (Model value : models) {
            if (Objects.equals(name, value.name)) {
                throw new DuplicateModelNameException("Name " + name + " has already exist");
            }
        }
        Model model = new Model(name, price);
        models = Arrays.copyOf(models, models.length + 1);
        models[models.length - 1] = model;
    }

    public void removeModel(String name) throws NoSuchModelNameException {
        boolean term = false;
        int index = 0;
        for (int i = 0; i < models.length; i++) {
            if (Objects.equals(name, models[i].name)) {
                System.arraycopy(models, index + 1, models, index, models.length - index - 1);
                models = Arrays.copyOf(models, models.length - 1);
                term = true;
                break;
            }
        }
        if (!term)
            throw new NoSuchModelNameException("Model with name: " + name + " doesn't exist");
    }

    public int getSize() {
        return models.length;
    }
}







