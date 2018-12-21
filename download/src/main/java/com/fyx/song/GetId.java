/**
 * 
 */
package com.fyx.song;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * 2018年5月15日
 *
 * @author ayue
 */
public class GetId {

        public static void main(String[] args) throws IOException {
                BufferedReader br = new BufferedReader(new FileReader("D:\\log\\html.txt"));
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\log\\song.txt")));
                String s;
                Set<Integer> iii = new HashSet<Integer>();
                while ((s = br.readLine()) != null) {
                        //截取
                        String[] outResult = s.split("\\*");
                        for (int i = 0; i < outResult.length; i++)
                                out.write(outResult[i] + "\n");
                        //截取
                        try {
                                int i = Integer.parseInt(s);
                                out.write(s + "\n");
                        } catch (Exception e) {

                        }
                        //去重
                        iii.add(Integer.parseInt(s));

                }
                for (int string : iii) {
                        out.write(String.valueOf(string) + "\n");
                }
                if (out.checkError()) {
                        System.out.println("日志重建异常！data:" + out);
                }
                br.close();
                out.close();
        }
}
