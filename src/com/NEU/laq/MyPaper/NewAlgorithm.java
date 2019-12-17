package com.NEU.laq.MyPaper;

import java.text.DecimalFormat;
import java.util.Arrays;

public class NewAlgorithm {
    // 总信任等级
    private int n;

    public NewAlgorithm(int n) {
        this.n = n;
    }

    public double[] getTrustInfo(Level level1, Level level2) throws Exception{
        if(n != level1.getN() || n != level2.getN())
            throw new Exception("信任等级必须一致");
        double[] trustInfo = new double[2 * n + 1];
        int index = 0;
        for(double d : calTrust(level1,level2)){
            trustInfo[index] = d;
            index ++;
        }
        // 跳过中立得分
        trustInfo[index] = 0.0;
        index ++;
        for(double d : calDistrust(level1,level2)){
            trustInfo[index] = d;
            index ++;
        }
        trustInfo[n] =  Double.parseDouble(new DecimalFormat("0.000").format(
                1 - Arrays.stream(trustInfo).reduce(0,Double::sum)));
        return trustInfo;

    }

    private double[] calTrust(Level level1,Level level2){
        double[] trust = new double[n];
        double[] level1Trust = level1.getTrust();
        double[] level2Trust = level2.getTrust();
        DecimalFormat df = new DecimalFormat("0.000");
        for(int i = 0; i < n; i++){
            // 计算第 i 级信任
            double weighted = 2.0 / ((n - i + 1) * (n - i));
            int multi = 1;
            double finalSum = 0.0;
            // 第一部分计算
            double sum = 0.0;
            for(int j = i; j < n; j ++){
                sum += multi * level2Trust[j];
                multi ++;
            }
            finalSum += sum * level1Trust[i];
            // 第二部分计算
            sum = 0.0;
            multi = 2;
            for(int j = i + 1; j < n; j++){
                sum += multi * level1Trust[j];
                multi ++;
            }
            finalSum += sum * level2Trust[i];
            trust[n - i - 1] = Double.parseDouble(df.format(finalSum * weighted));
        }
        return trust;
    }
    private double[] calDistrust(Level level1,Level level2){
        double[] distrust = new double[n];
        double[] level1Trust = level1.getTrust();
        double[] level2Trust = level2.getTrust();
        double[] level1Distrust = level1.getDistrust();
        double[] level2Distrust = level2.getDistrust();
        DecimalFormat df = new DecimalFormat("0.000");
        for(int i = 0; i < n; i++){
            // 计算第 i 级不信任
            double weighted = 2.0 / ((n - i + 1) * (n - i));
            double finalSum = 0.0;
            // 第一部分计算
            double sum = 0.0;
            int multi = 1;
            for(int j = i; j < n; j++){
                sum += level2Distrust[j] * multi;
                multi ++;
            }
            finalSum += sum * level1Trust[i];
            // 第二部分计算
            sum = 0.0;
            multi = 2;
            for(int j = i + 1; j < n; j++){
                sum += multi * level1Trust[j];
                multi ++;
            }
            finalSum += sum * level2Distrust[i];
            // 第三部分计算
            sum = 0.0;
            multi = 1;
            for(int j = i; j < n; j++){
                sum += level2Trust[j] * multi;
                multi ++;
            }
            finalSum += sum * level1Distrust[i];
            // 第四部分计算
            sum = 0.0;
            multi = 2;
            for(int j = i + 1; j < n; j++){
                sum += level1Distrust[j] * multi;
                multi ++;
            }
            finalSum += sum * level2Trust[i];
            // 计算中立对不信任的传播, 暂时忽略
            distrust[i] = Double.parseDouble(df.format(finalSum * weighted));

        }
        return distrust;
    }

    // 计算中立对不信任的传播
    private double calNeutralTran(double neutral, double part, double distrust){
        return neutral * part * distrust;
    }
}
