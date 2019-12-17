package com.NEU.laq.MyPaper;

import java.util.ArrayList;
import java.util.Arrays;

public class Level{
    private int n;
    private double[] distrust;
    private double[] trust;
    private double neutral;

    public Level(double[] trustData){
        n = trustData.length / 2;
        trust = Arrays.copyOfRange(trustData, 0, n);
        neutral = trustData[n];
        distrust = Arrays.copyOfRange(trustData, n + 1,trustData.length);
    }

    public Level(int n) {
        this.n = n;
        trust = new double[n];
        distrust = new double[n];
    }

    public Level(double[] distrust, double[] trust) {
        this.n = trust.length;
        this.distrust = distrust;
        this.trust = trust;
        neutral = 1 - Arrays.stream(distrust).reduce(0.0,Double :: sum)
                - Arrays.stream(trust).reduce(0.0,Double :: sum);
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double[] getDistrust() {
        return distrust;
    }

    public void setDistrust(double[] distrust) {
        this.distrust = distrust;
    }

    public double[] getTrust() {
        return trust;
    }

    public void setTrust(double[] trust) {
        this.trust = trust;
    }

    @Override
    public String toString() {
        return "Level{" +
                "distrust=" + Arrays.toString(distrust) +
                ", trust=" + Arrays.toString(trust) +
                ", neutral=" + neutral +
                '}';
    }
}
