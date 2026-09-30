package eda.practica1;
import java.util.Scanner;

public class Scaner {
        private final static Scanner sc  = new Scanner(System.in);

        public static Integer teclearInteger() {
            return Integer.valueOf(sc.nextLine());
        }
          public static String teclearString() {
            return sc.nextLine().trim();
        }
}
