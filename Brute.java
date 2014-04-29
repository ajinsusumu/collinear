import java.util.Arrays;

public class Brute {
    public static void main(String[] args) {
        //        String filename = args[0];
        String filename = "collinear/input8.txt";
        In in = new In(filename);
        int N = in.readInt();
        Point[] p = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            p[i] = new Point(x, y);
        }
        Arrays.sort(p);
        Out out = new Out();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (collinear(p[i], p[j], p[k])) {
                        for (int l = k + 1; l < N; l++) {
                            //                        out.printf("%d-%d-%d-%d%n",i,j,k,l);
                            if (collinear(p[i], p[j], p[l])) {
                                out.printf("%s->%s->%s->%s%n",
                                           p[i].toString(),
                                           p[j].toString(),
                                           p[k].toString(),
                                           p[l].toString()
                                           );
                            }
                        }
                    }
                }
            }
        }

    }
    static private boolean collinear(Point a, Point b, Point c, Point d) {
        if ((a.slopeTo(b) == a.slopeTo(c)) && (a.slopeTo(b) == a.slopeTo(d))) {
            return true;
        } else {
            return false;
        }
    }
    static private boolean collinear(Point a, Point b, Point c) {
        if (a.slopeTo(b) == a.slopeTo(c)) {
            return true;
        } else {
            return false;
        }
    }
}
