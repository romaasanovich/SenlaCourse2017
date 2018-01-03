package com.senla.autoservice.api;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface IManager  {


    void exportToCSV() throws IOException, NoSuchFieldException, IllegalAccessException;

    void importFromCSV() throws IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

}