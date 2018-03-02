package com.senla.autoservice.facade;

public class start {
    public static void main(String[] args) {
        Autoservice a = Autoservice.getInstance();
        System.out.println(a.getAllFreePlaces());
    }
}
