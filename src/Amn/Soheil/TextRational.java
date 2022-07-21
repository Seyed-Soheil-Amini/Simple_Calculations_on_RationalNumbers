package Amn.Soheil;

import java.util.Scanner;

public class TextRational {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int face, denominator;
        System.out.println("Enter the face : ");
        face = input.nextInt();
        input.nextLine();
        System.out.println("Enter the denominator : ");
        denominator = input.nextInt();
        input.nextLine();
        String str;
        Rational ans = new Rational();
        Rational test1 = new Rational(face, denominator);
        Rational test2 = new Rational(1, 2);
        Rational sum = test1.add(test2);
        Rational sub = test1.sub(test2);
        Rational mul = test1.mul(test2);
        Rational div = test1.div(test2);
        Rational rev = test1.reverse();
        System.out.print("The sum with 1/2: ");
        sum.print();
        System.out.print("The sub from 1/2: ");
        sub.print();
        System.out.print("The mul to 1/2: ");
        mul.print();
        System.out.print("The div by 1/2: ");
        div.print();
        System.out.print("The reverse of test1 : ");
        rev.print();
        System.out.print("Enter the command\n==>");
        str = input.nextLine();
        str = str.trim();
        ans = ans.convert(str);
        System.out.println("Final answer : " + ans);
    }
}
