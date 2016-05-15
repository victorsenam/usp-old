// Marcos Massayuki Kawakami - 8041331
// Problema da Conexidade 2D

import java.io.*;
import java.util.*;

public class PC2D {
    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        double d = Double.parseDouble(st.nextToken());
        C2DStructure c2d = new C2DStructure(d);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            c2d.addPoint(x, y);
        }
        System.out.println(c2d.isConnected() ? "Sim" : "Nao");
        // System.out.println(c2d.numComponents());
    }

    private static class C2DStructure {
        private ArrayList<Point> grid[][];
        private int G;
        private int nComponents;
        private double d, d2;

        public C2DStructure(double d) {
            this.d = d;
            this.d2 = d*d;
            this.G = (int) Math.ceil(1.0 / d);
            nComponents = 0;
            grid = (ArrayList<Point>[][]) new ArrayList[G + 2][G + 2];
            for (int i = 0; i <= G + 1; i++)
                for (int j = 0; j <= G + 1; j++)
                    grid[i][j] = new ArrayList<Point>();
        }

        public void addPoint(double x, double y) {
            nComponents++;
            Point p = new Point(x, y);
            int row = 1 + (int)(x*G);
            int col = 1 + (int)(y*G);
            outer: for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    for (Point q: grid[i][j]) {
                        if (p.distanceSquaredTo(q) <= d2) {
                            join(p, q);
                            if (nComponents == 1) break outer;
                        }
                    }
                }
            }
            grid[row][col].add(p);
        }

        public int numComponents() {
            return nComponents;
        }

        public boolean isConnected() {
            return nComponents == 1;
        }

        private class Point {
            public double x, y;
            public Point rep;
            public int rank;
            
            public Point(double x, double y) {
                this.x = x;
                this.y = y;
                this.rep = this;
                this.rank = 0;
            }

            public double distanceSquaredTo(Point that) {
                double dx = this.x - that.x;
                double dy = this.y - that.y;
                return dx*dx + dy*dy;
            }
        }
            
        private Point find(Point p) {            
            if (p == p.rep) return p;
            return p.rep = find(p.rep);
        }

        private void join(Point a, Point b) {
            Point pa = find(a), pb = find(b);
            if (pa == pb) return;
            nComponents--;
            if (pa.rank < pb.rank)
                pa.rep = pb;
            else if(pa.rank > pb.rank)
                pb.rep = pa;
            else {
                pa.rep = pb;
                pb.rank++;
            }
        }
    }
}
