import java.math.BigInteger;

public class ECC {
    // yˆ2 = xˆ3 + ax + b
    // Bitcoin a=0 and b=7 => yˆ2 = xˆ3 + 7
    private double a;
    private double b;

    public ECC(double a, double b) {
        this.a = a;
        this.b = b;
    }

    // it has O(n) linear running time complexity
    public Point doubleAndAdd(int n, Point p) {
        Point temp = new Point(p.getX(), p.getY());
        String nBinary = Integer.toBinaryString(n); // 10 -> ("1010")
        for (int i = 1; i < nBinary.length(); ++i) {
            int actualBit = Integer.parseInt(String.valueOf(nBinary.charAt(i)));

            // point doubling operation
            temp = pointAddition(temp, temp);

            if (actualBit == 1) {
                temp = pointAddition(temp, p);
            }
        }

        return temp;
    }

    public Point pointAddition(Point p, Point q) {
        double x1 = p.getX();
        double y1 = p.getY();
        double x2 = q.getX();
        double y2 = q.getY();

        double m;
        // sometimes we have to make point addition and
        // if P=Q then we need to do point doubling (see 12 page in presentation for more details)
        // Double.compare(x1,x2) == 0 means x1=x2
        if (Double.compare(x1, x2) == 0 && Double.compare(y1, y2) == 0) {
            // Point doubling because P=Q
            m = (3 * x1 * x1 + a) / (2 * y1);
        } else {
            // Point addition (P!=Q)
            m = (y2 - y1) / (x2 - x1);
        }

        // calculate point R(x,y)
        double x3 = m * m - x2 - x1;
        double y3 = m * (x1 - x3) - y1;

        return new Point(x3, y3);
    }
}
