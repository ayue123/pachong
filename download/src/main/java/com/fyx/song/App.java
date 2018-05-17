package com.fyx.song;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
        public static void main(String[] args) {
                Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                Integer mapp = map.get(1);
                map.remove(2);
                System.out.println(mapp);
                if (map != null) {
                        System.out.println(11);
                }
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd -HH");//设置日期格式
                System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

                System.out.println(33 % 90);

                int a = 5;
                a *= (100d - 20) / 100d;
                System.out.println(a);

        }
}
