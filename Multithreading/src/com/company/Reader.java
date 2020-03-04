package com.company;

import java.util.List;

public class Reader implements Runnable{
    private static List container;
    private static int index = 0;
    private static boolean canBeRemoved ;

    public static void setIsInserted(){
        canBeRemoved = true;
    }
    public Reader(List list){
        container = list;
    }
    @Override
    public void run(){
        while(true) {

            if (!(container.isEmpty()) && canBeRemoved) {

                var value = container.remove(0);
                System.out.println("Removing " + value);

                Writer.decrement();
                canBeRemoved = false;
            }try{
                Thread.sleep(1);

            }catch (InterruptedException e){
                e.getMessage();
            }

        }
    }


}
