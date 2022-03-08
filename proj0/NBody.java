// import edu.princeton.cs.introcs.In;

public class NBody {
    public static void main(String[] args) {
        // Store the command line arguments
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        // 读取宇宙半径以及 planets
        double R = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        // 绘制背景
        StdDraw.picture(0.5, 0.5, "./images/starfield.jpg");

        // Drawing All of the Planets
        for (Planet p : planets)
            p.draw();

        // Create an Animation
        StdDraw.enableDoubleBuffering();    // 防止画面闪动
        int len = planets.length;
        double[] xForce = new double[len];
        double[] yForce = new double[len];
        for (double time = 0; time < T; time += dt) {
            // 计算合力
            for (int i = 0; i < len; i++) {
                xForce[i] = planets[i].calcNetForceExertedByX(planets);
                yForce[i] = planets[i].calcNetForceExertedByY(planets);
            }
            // 更新坐标以及速度
            for (int i = 0; i < len; i++) {
                planets[i].update(dt, xForce[i], yForce[i]);
            }
            // 形状绘制
            StdDraw.picture(0.5, 0.5, "./images/starfield.jpg");
            for (Planet p : planets)
                p.draw();
            StdDraw.show();
//            StdDraw.pause(10);  // Pause for 10 ms
        }

        // 打印最终状态
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", R);
        for (Planet planet : planets) {
            StdOut.printf(
                    "%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos,
                    planet.yyPos,
                    planet.xxVel,
                    planet.yyVel,
                    planet.mass,
                    planet.imgFileName
            );
        }
    }

    /**
     * Read the radius of the universe from files.
     * @param fileName target file
     * @return universe radius R
     */
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }


    /**
     * Read the planets from files.
     * @param fileName target file
     * @return Planet array
     */
    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int size = in.readInt();
        in.readDouble();    // 吞掉宇宙半径

        Planet[] planets = new Planet[size];
        for (int i = 0; i < size; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return planets;
    }
}
