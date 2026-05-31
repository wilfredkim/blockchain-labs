package com.wilfred.blockchain.blockchain.ellipticcurve;

public class ECC {
    private double a;
    private double b;

    public ECC(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public Point addPoints(Point p1, Point p2) {
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        double m = 0;
        if (Double.compare(x1, x2) == 0 && Double.compare(y1, y2) == 0) {
            //point doubling
            m = (3 * x1 * x1 + a) / (2 * y1);
        } else {
            //point addition
            m = (y2 - y1) / (x2 - x1);
        }
        // we can calculate point R
        double x3 = m * m - x1 - x2;
        double y3 = m * (x1 - x3) - y1;
        return new Point(x3, y3);
    }

    public Point doubleAndAdd(int n, Point p) {
        Point temp = new Point(p.getX(), p.getY()); // Identity element
        String binaryN = Integer.toBinaryString(n);
        for (int i = 1; i < binaryN.length(); ++i) {
            temp = addPoints(temp, temp); // Point doubling
            if (binaryN.charAt(i) == '1') {
                temp = addPoints(temp, p); // Point addition
            }
        }
        return temp;
    }
}
