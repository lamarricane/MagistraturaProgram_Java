package lab_2.transports;

import lab_2.exceptions.*;
import java.io.Serializable;

public interface Transport extends Serializable {

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