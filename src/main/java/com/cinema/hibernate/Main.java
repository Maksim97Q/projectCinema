package com.cinema.hibernate;

public class Main {
    public static void main(String[] args) {
        Util util = new Util();
        util.delete(new Worker(3,"max13", 32, 500));

//        System.out.println(util.read(new Worker("max13", 55)));


//        util.update(new Worker(2, "maks000", 11, 5000));

//        util.add(new Worker("maksim9", 33));

//        util.readAll();
//        util.workerList1.forEach(System.out::println);
    }
}
