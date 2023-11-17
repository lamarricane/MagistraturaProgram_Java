package lab_4.transports;

import lab_4.exceptions.NoSuchModelNameException;
import lab_4.exceptions.DuplicateModelNameException;


import java.io.Serializable;

public interface Transport extends Serializable, Cloneable {

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