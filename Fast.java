import java.util.Arrays;

public class Fast {
    public static void main(String[] args) {
        String filename;
        if (args.length < 1) {
            //                          filename = "collinear/input50.txt";
            //            filename = "collinear/rs1423.txt";
            //        filename = "collinear/input10.txt";
//            filename = "collinear/input20.txt";
            filename = "collinear/input50.txt";
            //            filename = "collinear/input8.txt";
            //                      filename = "collinear/input9.txt";
        } else {
            filename = args[0];
        }
        Out out = new Out();

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        In in = new In(filename);
        int N = in.readInt();
        Point[] p = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            p[i] = new Point(x, y);
            p[i].draw();

        }
        double[] sl = new double[N];
        for (int i = 0; i < N - 3; i++) {
            Arrays.sort(p, i + 1, N, p[i].SLOPE_ORDER);
            Arrays.sort(p, 0, i, p[i].SLOPE_ORDER);

            for (int j = 0; j < N; j++) sl[j] = p[i].slopeTo(p[j]);
            int count = 1;
            int ii = 0;
            for (int k = i + 2; k < N; k++) {
                if (sl[k - 1] == sl[k]) {
                    while (sl[ii] < sl[k] && ii < i) ii++;
                    if (sl[ii] != sl[k]) count++;
                } else {
                    if (count > 2) segment_out(i, k - count, count, p, out);
                    count = 1;
                }
            }
            if (count > 2) segment_out(i, N - count, count, p, out);
        }
        StdDraw.show(0);
    }
    static private void segment_out(int o, int g, int count, Point[] q, Out out) {
        Point[] r = new Point[count + 1];
        r[0] = q[o];
        for (int l = 1; l < count + 1; l++) r[l] = q[l + g - 1];
        Arrays.sort(r);
        for (int l = 0; l < count; l++) out.printf("%s -> ", r[l]);
        out.printf("%s%n",r[count]);
        r[0].drawTo(r[count]);
    }
}
