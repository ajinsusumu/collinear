import java.util.Arrays;

public class Fast {
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
        //        Arrays.sort(p);
        Out out = new Out();
        //        int j = 0;
        for (int i = 0; i < N - 3;) {
            //            Point[] q = new Point[N - i];
            //            for (int j = 0; j < N - i; j++) q[j] = p[j + i];
            Arrays.sort(p, i + 1, N, p[i].SLOPE_ORDER);
            int count = 1;
            int h = i + 1;
            for (int k = i + 2; k < N; k++) {
                if (p[i].slopeTo(p[k - 1]) == p[i].slopeTo(p[k])) {
                    count++;
                } else {
                    if (count > 2) { segment_out(i, h, k - count, count, p, out); h += count; }
                    count = 1;
                }
            }
            if (count > 2) { segment_out(i, h, N - i - count, count, p, out); h += count; }
            i = h;
        }
    }
    static private void segment_out(int o, int h, int g, int count, Point[] q, Out out) {
        Point[] r = new Point[count + 1];
        r[0] = q[o];
        for (int l = 1; l < count + 1; l++) r[l] = q[l + g];
        Arrays.sort(r);
        for (int l = 0; l < count; l++) out.printf("%s -> ", r[l]);
        out.printf("%s%n",r[count]);

        /* move _count_ Points starting from g to o+1*/
        for (int i = g + count - 1, j = h; i >= g && j < g; i--, j++) {
            Point t = q[i];
            q[i] = q[j];
            q[j] = t;
        }
    }
    static private void segment_out1(int g, int count, Point[] q, Out out) {
        Point[] r = new Point[count+1];
        r[0] = q[0];
        for (int l = 1; l < count+1; l++) r[l] = q[l + g];
        Arrays.sort(r);
        for (int l = 0; l < count; l++) out.printf("%s -> ", r[l]);
        out.printf("%s%n",r[count]);
    }
}
