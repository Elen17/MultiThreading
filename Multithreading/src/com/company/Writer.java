package com.company;

import java.util.List;
import java.util.Random;

public class Writer implements Runnable {
    private static final int DEFAULT_CAPACITY = 10;
    private static List container;
    private static volatile int capacity;

    static {
        capacity = 0;
    }
    public Writer(List list){
        container = list;
    }
    public static void decrement(){
        if(capacity > 0 && capacity < DEFAULT_CAPACITY){
            --capacity;
        }
    }

    @Override
    public void run() {
        while (true){

            if(capacity < DEFAULT_CAPACITY){
                var value = (int)(Math.random() * 100);
                System.out.println("Inserting " + value);
                container.add(value);
                ++capacity;
                Reader.setIsInserted();
                try{
                    Thread.sleep(3);
                }catch (InterruptedException e){
                    e.getMessage();
                }
            }
        }
    }
}
