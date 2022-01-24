package com.cinema.hibernate;

import org.hibernate.Session;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Util util = new Util();
//        util.delete(new Worker(4,"max", 32, 500));

//        util.read(new Worker("max", 12, 55));


//        util.update(new Worker(2, "maks000", 11, 5000));

        util.add(new Worker("maksim9", 33));

//        util.readAll();
//        util.workerList1.forEach(System.out::println);
    }
}
