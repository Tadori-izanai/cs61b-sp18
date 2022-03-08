//import com.sun.source.tree.ReturnTree;

import javax.management.ListenerNotFoundException;
import java.lang.management.PlatformManagedObject;
import java.util.ConcurrentModificationException;

public class Planet {
//    public static final double G = 6.67e-11;    // 在类中应该将任何常量声明为 static final
    private static final double G = 6.67e-11;    // gradescope 提示使用 private

    // instance variables
    public double xxPos;           // current x position
    public double yyPos;           // current y position
    public double xxVel;           // current velocity in the x direction
    public double yyVel;           // current velocity in the y direction
    public double mass;            // its mass
    public String imgFileName;     // 描述行星的图象的文件名

    // constructors
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }


    /**
     * Calculate the distance.
     * @param p target planet
     * @return the distance between this and p
     */
    public double calcDistance(Planet p) {
        double deltaX = p.xxPos - this.xxPos;
        double deltaY = p.yyPos - this.yyPos;
        return (Math.sqrt(deltaX * deltaX + deltaY * deltaY));
    }

    /**
     * Calculate the force.
     * @param p target planet
     * @return p 对 this 施加的引力大小
     */
    public double calcForceExertedBy(Planet p) {
        double distance = this.calcDistance(p);
        return (G * this.mass * p.mass / (distance * distance));
    }


    /**
     * Calculate the force in x direction.
     * @param p target planet
     * @return p 对 this 施加的引力 (x 方向的分量)
     */
    public double calcForceExertedByX(Planet p) {
        double deltaX = p.xxPos - this.xxPos;
        double distance = this.calcDistance(p);
        double force = this.calcForceExertedBy(p);
        return (force * deltaX / distance);
    }
    /**
     * Calculate the force in y direction.
     * @param p target planet
     * @return p 对 this 施加的引力 (y 方向的分量)
     */
    public double calcForceExertedByY(Planet p) {
        double deltaY = p.yyPos - this.yyPos;
        double distance = this.calcDistance(p);
        double force = this.calcForceExertedBy(p);
        return (force * deltaY / distance);
    }

    /**
     * Calculate the net force in x direction.
     * @param pArray target planets
     * @return planets 对 this 施加的引力合力的 x 分量
     */
    public double calcNetForceExertedByX(Planet[] pArray) {
        double netForceX = 0;
        for (Planet p : pArray) {
            if (p.equals(this))     // 使用了 equals() method, 当两个 planet 一致的时候, 返回 true
                continue;
            netForceX += this.calcForceExertedByX(p);
        }
        return netForceX;
    }
    /**
     * Calculate the net force in y direction
     * @param pArray target planets
     * @return planets 对 this 施加的引力合力的 y 分量
     */
    public double calcNetForceExertedByY(Planet[] pArray) {
        double netForceY = 0;
        for (Planet p : pArray) {
            if (p.equals(this))
                continue;
            netForceY += this.calcForceExertedByY(p);
        }
        return netForceY;
    }

    /**
     * Update the planet's velocity and position.
     * @param dt 一段微小的时间
     * @param fX this 的 x 方向受力
     * @param fY this 的 y 方向受力
     */
    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += aX * dt;
        yyVel += aY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }


    /**
     * Draw the planet at its current position.
     */
    public void draw() {
        double R = NBody.readRadius("./data/planets.txt");
        double x = xxPos / R / 2 + 0.5;
        double y = yyPos / R / 2 + 0.5;
        StdDraw.picture(x, y, "./images/" + imgFileName);
    }


}
/*
1. 所有 number 都是 double
2. 所有 instance variables / methods 都是 public
 */

