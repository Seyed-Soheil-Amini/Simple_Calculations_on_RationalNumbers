package Amn.Soheil;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String str;
        Rational ans = new Rational();
        System.out.print("Enter your command\n==>");
        str = input.nextLine();
        str = str.trim();
        ans = ans.convert(str);
//        ans.print();
        System.out.println("Final answer : " + ans);
        System.exit(0);
    }
}
