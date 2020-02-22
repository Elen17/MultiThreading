package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List container = new ArrayList();
        Thread writer = new Thread(new Writer(container));
        System.out.println("Process starts: ");
        writer.start();
        Thread reader = new Thread(() -> {
            try {
                Thread.sleep(2);
                new Reader(container).run();

            }catch(InterruptedException e){
                System.out.println(e.getMessage());
            }

        });
        reader.start();
    }
}
