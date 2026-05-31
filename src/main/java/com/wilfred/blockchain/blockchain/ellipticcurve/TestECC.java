package com.wilfred.blockchain.blockchain.ellipticcurve;

public class TestECC {
    public static void main(String[] args) {
        ECC ecc = new ECC(0, 7);
        Point p1 = new Point(3, 6);
        Point p2 = new Point(10, 7);
        Point result = ecc.addPoints(p1, p2);
        System.out.println("Result of point addition: " + result.getX() + ", " + result.getY());
        int n = 5;
        Point result2 = ecc.doubleAndAdd(n, p1);
        System.out.println("Result of double and add: " + result2.getX() + ", " + result2.getY());
    }
}
