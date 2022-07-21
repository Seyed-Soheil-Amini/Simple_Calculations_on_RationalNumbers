package Amn.Soheil;

/**
 * Author:Seyed Mohammad Soheil Amini
 */
public class Rational {
    private int face;//the face of rational number
    private int denominator;//the denominator of rational number

    /**
     * first constructor
     * this constructor while has been called builds a rational number with zero amount
     */
    public Rational() {
        face = 0;
        denominator = 1;
    }

    /**
     * second constructor
     * this constructor while has been called builds a rational number with Input Arguments
     *
     * @param face
     * @param denominator
     */
    public Rational(int face, int denominator) {
        simplify(face, denominator);
    }

    /**
     * this function simplifies the rational number accordingly to GCD of face and denominator
     *
     * @param face
     * @param denominator
     */
    public void simplify(int face, int denominator) {
        int gcd = Math.abs(GCD(face, denominator));//calculating the gcd and saving in a variable
        //divide face and denominator by gcd
        this.face = face / gcd;
        this.denominator = denominator / gcd;
    }

    /**
     * this function calculates the gcd of two numbers Recursively
     *
     * @param a first number
     * @param b second number
     * @return result of calculation
     */
    public int GCD(int a, int b) {
        if (b == 0)
            return a;
        return GCD(b, a % b);
    }

    /**
     * this function adds two rational numbers together
     *
     * @param num
     * @return result of adding two numbers
     */
    public Rational add(Rational num) {
        Rational sum = new Rational();
        int tempGcd, fc1, fc2;
        //Common denominator operations with tempGcd
        if (this.denominator > num.denominator) {
            tempGcd = GCD(this.denominator, num.denominator);
            sum.denominator = (this.denominator * num.denominator) / tempGcd;
        } else {
            tempGcd = GCD(num.denominator, this.denominator);
            sum.denominator = (this.denominator * num.denominator) / tempGcd;
        }
        fc1 = this.face * (sum.denominator / this.denominator);
        fc2 = num.face * (sum.denominator / num.denominator);
        sum.face = fc1 + fc2;
        //simplify the result
        sum.simplify(sum.face, sum.denominator);
        return sum;
    }

    /**
     * this function subtracts two rational numbers together
     *
     * @param num
     * @return result of subtracting two numbers
     */
    public Rational sub(Rational num) {
        Rational sub = new Rational();
        int tempGcd, fc1, fc2;
        //Common denominator operations with tempGcd
        if (this.denominator > num.denominator) {
            tempGcd = GCD(this.denominator, num.denominator);
            sub.denominator = (this.denominator * num.denominator) / tempGcd;
        } else {
            tempGcd = GCD(num.denominator, this.denominator);
            sub.denominator = (this.denominator * num.denominator) / tempGcd;
        }
        fc1 = this.face * (sub.denominator / this.denominator);
        fc2 = num.face * (sub.denominator / num.denominator);
        sub.face = fc1 - fc2;
        //simplify the result
        sub.simplify(sub.face, sub.denominator);
        return sub;
    }

    /**
     * this function multiplies two rational numbers
     *
     * @param num
     * @return result of multiplying two numbers
     */
    public Rational mul(Rational num) {
        Rational mul = new Rational();
        mul.face = this.face * num.face;
        mul.denominator = this.denominator * num.denominator;
        //simplify the result
        mul.simplify(mul.face, mul.denominator);
        return mul;
    }

    /**
     * this function divides one rational number by another
     *
     * @param num
     * @return result of dividing two numbers
     */
    public Rational div(Rational num) {
        Rational div = new Rational();
        //invert the second number
        num = num.reverse();
        div.face = this.face * num.face /*num.denominator*/;
        div.denominator = this.denominator * num.denominator /*num.face*/;
        //simplify the result
        div.simplify(div.face, div.denominator);
        return div;
    }

    /**
     * this function shows the rational number
     */
    public void print() {
        System.out.println(face + "/" + denominator);
    }

    /**
     * this function inverses a rational number
     *
     * @return inversely rational number
     */
    public Rational reverse() {
        Rational rev = new Rational(this.denominator, this.face);
        return rev;
    }

    /**
     * this function returns a string that shows a rational number
     * similar toString function
     *
     * @return the rational number string
     */
    public String info() {
        return String.valueOf(this.face) + "/" + String.valueOf(this.denominator);
    }

    /**
     * this function return a string that shows a rational number
     *
     * @return the string including a rational number
     */
    public String toString() {
        return String.format("%d/%d", face, denominator);
    }

    /**
     * this function performs calculations by taking a set of operators and rational numbers
     * according to the priority of the operators and returns the result as a rational number.
     * first, according to the priority of the multiplication and division operators
     * it seeks out the multiplication and division operators
     * Converts an expressive number to a string and substitutes the two numbers and the operator between them.
     * then, according to the priority of addition and subtraction operators
     * first it looks for addition and subtraction operators, whenever it finds one of them,
     * it takes two rational numbers before and after it out of the string
     * and performs the calculation and then the result Converts an expressive number to a string
     * and substitutes the two numbers and the operator between them.
     *
     * @param command a string containing commands
     * @return the result of the calculations
     */
    public static Rational convert(String command) {
        int signIndex = 3, decIndex, incIndex, firstIndex = 0, lastIndex = 6;
        //this loop is for initialization to the final expressive number
        for (int i = command.indexOf('/') + 1; i < command.length(); i++) {
            if (48 <= command.charAt(i) && command.charAt(i) <= 57) {
                lastIndex = i;
            } else
                break;
        }
        //Initialize the final variable
        Rational ans = new Rational(Integer.parseInt(command.substring(0, command.indexOf('/'))), Integer.parseInt(command.substring(command.indexOf('/') + 1, lastIndex + 1)));
        char tempCh = ' ';
        //Start search operations for multiplication and division operators
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == '*' || command.charAt(i) == ':') {
                decIndex = 1;
                incIndex = 1;
                tempCh = command.charAt(i);
                signIndex = command.indexOf(tempCh);
                while (true) {
                    if (signIndex < decIndex) {
                        firstIndex = signIndex - decIndex + 1;
                        break;
                    }
                    if ((48 <= command.charAt(signIndex - decIndex) && command.charAt(signIndex - decIndex) <= 57) || command.charAt(signIndex - decIndex) == '/') {
                        decIndex++;
                    } else if (signIndex - decIndex >= 0 && command.charAt(signIndex - decIndex) == ' ')
                        decIndex++;
                    else {
                        firstIndex = signIndex - decIndex + 1;
                        break;
                    }
                }
                while (true) {
                    if (signIndex + incIndex >= command.length()) {
                        lastIndex = signIndex + incIndex - 1;
                        break;
                    }
                    if ((48 <= command.charAt(signIndex + incIndex) && command.charAt(signIndex + incIndex) <= 57) || command.charAt(signIndex + incIndex) == '/') {
                        incIndex++;
                    } else if (signIndex + incIndex < command.length() && command.charAt(signIndex + incIndex) == ' ')
                        incIndex++;
                    else {
                        lastIndex = signIndex + incIndex - 1;
                        break;
                    }
                }
                //Example:"1/3*5/9"
                //==> str1:"1/3"
                //==> str2:"5/9"
                String[] str1 = (command.substring(firstIndex, signIndex)).split("/");
                String[] str2 = (command.substring(signIndex + 1, lastIndex + 1)).split("/");
                for (int v = 0; v < 2; v++) {
                    str1[v] = str1[v].trim();
                    str2[v] = str2[v].trim();
                }
                //Convert the contents of each string to a rational number
                Rational object1 = new Rational(Integer.parseInt(str1[0]), Integer.parseInt(str1[1]));
                Rational object2 = new Rational(Integer.parseInt(str2[0]), Integer.parseInt(str2[1]));
                ans = new Rational();
                if (tempCh == '*')
                    ans = object1.mul(object2);
                else
                    ans = object1.div(object2);
                //replace "5/27" to "1/3*5/9"
                //result:==>"5/27"
                command = command.replace(command.substring(firstIndex, lastIndex + 1), ans.toString());
                i = -1;//To search from beginning of the string
            }
        }
        //Start search operations for addition and subtraction operators
        for (int i = 1; i < command.length(); i++) {
            if ((command.charAt(i) == '+' || command.charAt(i) == '-') && countSlash(command) > 1) {
                decIndex = 1;
                incIndex = 1;
                tempCh = command.charAt(i);
                signIndex = command.indexOf(tempCh, 1);
                while (true) {
                    if (signIndex < decIndex) {
                        firstIndex = signIndex - decIndex + 1;
                        break;
                    }
                    if ((48 <= command.charAt(signIndex - decIndex) && command.charAt(signIndex - decIndex) <= 57) || command.charAt(signIndex - decIndex) == '/') {
                        decIndex++;
                    } else if (signIndex - decIndex == 0 && command.charAt(signIndex - decIndex) == '-') {
                        decIndex++;
                    } else if (signIndex - decIndex >= 0 && command.charAt(signIndex - decIndex) == ' ')
                        decIndex++;
                    else {
                        firstIndex = signIndex - decIndex + 1;
                        break;
                    }
                }
                while (true) {
                    if (signIndex + incIndex >= command.length()) {
                        lastIndex = signIndex + incIndex - 1;
                        break;
                    }
                    if ((48 <= command.charAt(signIndex + incIndex) && command.charAt(signIndex + incIndex) <= 57) || command.charAt(signIndex + incIndex) == '/') {
                        incIndex++;
                    } else if (signIndex + incIndex < command.length() && command.charAt(signIndex + incIndex) == ' ')
                        incIndex++;
                    else {
                        lastIndex = signIndex + incIndex - 1;
                        break;
                    }
                }
                //Example:"1/3-5/9"
                //==> str1:"1/3"
                //==> str2:"5/9"
                String[] str1 = (command.substring(firstIndex, signIndex)).split("/");
                String[] str2 = (command.substring(signIndex + 1, lastIndex + 1)).split("/");
                for (int v = 0; v < 2; v++) {
                    str1[v] = str1[v].trim();
                    str2[v] = str2[v].trim();
                }
                //Convert the contents of each string to a rational number
                Rational object1 = new Rational(Integer.parseInt(str1[0]), Integer.parseInt(str1[1]));
                Rational object2 = new Rational(Integer.parseInt(str2[0]), Integer.parseInt(str2[1]));
                ans = new Rational();
                if (tempCh == '+')
                    ans = object1.add(object2);
                else
                    ans = object1.sub(object2);
                String old = (command.substring(firstIndex, lastIndex + 1));
                command = command.replace(command.substring(firstIndex, lastIndex + 1), ans.toString());
                //replace "-2/9" to "1/3-5/9"
                //result:==>"-2/9"
                i = 0;//To search from beginning of the string
            }
        }
        //System.out.println(command);
        return ans;
    }

    /**
     * this function counts the numbers of slash in the string that includes some rational numbers and operators
     *
     * @param str the command string
     * @return the number of slash in string
     */
    private static int countSlash(String str) {
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '/')
                counter++;
        }
        return counter;
    }
}
