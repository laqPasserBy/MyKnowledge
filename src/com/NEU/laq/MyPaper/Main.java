package com.NEU.laq.MyPaper;

import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception{
        int n = 2;
        NewAlgorithm myAlgo = new NewAlgorithm(n);
        try(FileWriter fileWriter = new FileWriter(new File("calculate.txt"))) {
            for (int i = 0; i < 1000; i++) {
                int m = 2 * n + 1;
                double[] dou1 = generateDoubleArray(m);
                double[] dou2 = generateDoubleArray(m);
                Level level1 = new Level(dou1);
                Level level2 = new Level(dou2);
//              System.out.println(level1);
//              System.out.println(level2);
                double[] res = myAlgo.getTrustInfo(level1, level2);

                StringBuffer sb = new StringBuffer();
                sb.append("(");
                for (int j = 0; j < m; j++)
                    sb.append(dou1[j] + ", ");
                sb.replace(sb.length() - 2, sb.length(), ")");
                sb.append(" + (");
                for (int j = 0; j < m; j++)
                    sb.append(dou2[j] + ", ");
                sb.replace(sb.length() - 2, sb.length(), ")");
                sb.append(" = (");
                for (int j = 0; j < m; j++)
                    sb.append(res[j] + ", ");
                sb.replace(sb.length() - 2, sb.length(), ")");
                sb.append("    sum = " + new DecimalFormat("0.000").format(Arrays.stream(res).reduce(0.0, Double::sum)) + "\n\n");
                //System.out.println(sb);
                fileWriter.write(sb.toString());
            }
        }


//        // 测试是否可以生成一组 和为1 的浮点数数组
//        double[] dou = generateDoubleArray(5);
//        for(double d : dou)
//            System.out.print(d + " ");
//        System.out.println();
//        System.out.println(Arrays.stream(dou).reduce(0.0, Double :: sum));


    }

    public static double[] generateDoubleArray(int n){
        double[] res = new double[n];
        Random random = new Random();
        for(int i = 0; i < n; i++)
            res[i] = random.nextDouble();
        double sum = Arrays.stream(res).reduce(0.0,Double :: sum);
        for(int i = 0; i < n; i++)
            res[i] = Double.parseDouble(new DecimalFormat("0.000").format(res[i] / sum));
        return res;
    }
}


