package com.fyx.song;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class JunTest {
        @Test
        public void testaaa() {
                String s1 = "1234";
                String s2 = "1234";
                if (s1 == s2) {
                        System.out.println("s1 == s2");
                }
                if (s1.equals(s2)) {
                        System.out.println("s1.equals(s2)");
                }
                Testbb testbb1 = new Testbb();
                testbb1.setSs("qwer");
                Testbb testbb2 = new Testbb();
                testbb2.setSs("qwer");
                if (testbb1 == testbb2) {
                        System.out.println("testbb1 == testbb2");
                }
                if (testbb1.equals(testbb2)) {
                        System.out.println("testbb1.equals(testbb2)");
                }

                Integer i1 = 2;
                Integer i2 = 2;
                if (i1 == i2) {
                        System.out.println("i1 == i2");
                }
                Set<String> sss = new HashSet<String>();

        }

        class Testbb {
                String ss;

                /**
                 * @return the ss
                 */
                public String getSs() {
                        return ss;
                }

                /**
                 * @param ss
                 *                the ss to set
                 */
                public void setSs(String ss) {
                        this.ss = ss;
                }

                /* (non-Javadoc)
                 * @see java.lang.Object#hashCode()
                 */
                @Override
                public int hashCode() {
                        final int prime = 31;
                        int result = 1;
                        result = prime * result + getOuterType().hashCode();
                        result = prime * result + ((ss == null) ? 0 : ss.hashCode());
                        return result;
                }

                @Override
                public boolean equals(Object obj) {
                        if (this == obj)
                                return true;
                        if (obj == null)
                                return false;
                        if (getClass() != obj.getClass())
                                return false;
                        Testbb other = (Testbb) obj;
                        if (!getOuterType().equals(other.getOuterType()))
                                return false;
                        if (ss == null) {
                                if (other.ss != null)
                                        return false;
                        } else if (!ss.equals(other.ss))
                                return false;
                        return true;
                }

                private JunTest getOuterType() {
                        return JunTest.this;
                }

        }
        //        public static void main(String[] args) {
        //                List<App> aaa = new LinkedList<App>();
        //                App app1 = new App();
        //                App app2 = new App();
        //                App app3 = new App();
        //                aaa.add(app1);
        //                aaa.add(app2);
        //                aaa.add(app3);
        //                ;
        //
        //                new Thread(new Runnable() {
        //
        //                        @Override
        //                        public void run() {
        //                                for (int i = 0; i <= 1000; i++) {
        //                                        aaa.add(new App());
        //                                }
        //                        }
        //
        //                }).start();
        //
        //                for (Iterator<App> it = aaa.iterator(); it.hasNext();) {
        //                        App a = it.next();
        //                        it.remove();
        //                }
        //        }

}
