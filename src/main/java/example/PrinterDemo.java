package example;

import java.util.Date;
import java.util.Locale;

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

        printf("It's %TA, %<tH:%<tM. It's %s", new Date(), "good");
        printf(Locale.forLanguageTag("RU"), "%n%,.2f", 10000.5);
    }
}

