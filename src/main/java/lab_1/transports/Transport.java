package lab_1.transports;

import lab_1.exceptions.*;

public interface Transport {

    String getMark();

    void setMark(String mark);

    String[] getAllModelNames();

    double[] getAllModelPrices();

    double getModelPriceByName(String name) throws NoSuchModelNameException;

    void modifyModelName(String name, String new_name) throws NoSuchModelNameException, DuplicateModelNameException;

    void modifyModelPriceByName(String name, double new_price) throws NoSuchModelNameException;

    void addModel(String name, double price) throws DuplicateModelNameException;

    void removeModel(String name) throws NoSuchModelNameException;

    int getSize();
}