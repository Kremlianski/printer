package example;

import static ru.exxo.jutil.Printer.*;
public class PrinterDemo {
    private static class Demo {
        public Demo(String str) {
            this.str = str;
        }

        private String str;

        @Override
        public String toString() {
            return "Demo{" +
                    "str='" + str + '\'' +
                    '}';
        }
    }
    public static void main(String[] args) {

        Demo demo = new Demo("It's a Demo");
        println("I can print!");
        println(10);
        println(true);
        println(demo);
    }
}

