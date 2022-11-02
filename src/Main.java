import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Calc calculator = new Calc(input);
        calculator.Start();

    }
}

class Calc {
    String str;
    HashMap<String, Integer> romanNumbers = new HashMap<>();
    public Calc(String str) {
        this.str = str;
        romanNumbers.put("I", 1);
        romanNumbers.put("II", 2);
        romanNumbers.put("III", 3);
        romanNumbers.put("IV", 4);
        romanNumbers.put("V", 5);
        romanNumbers.put("VI", 6);
        romanNumbers.put("VII", 7);
        romanNumbers.put("VIII", 8);
        romanNumbers.put("IX", 9);
        romanNumbers.put("X", 10);
    }
    public String[] formatStr() {
        str = str.replace(" ", "");
        return str.split("(?=[+\\-*/])|(?<=[+\\-*/])");
    }
    public String arabToRoman (int number) {
        String[] keys = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int[] values =  { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < keys.length; i++) {
            while(number >= values[i]) {
                int temp = number / values[i];
                number = number % values[i];
                answer.append(keys[i].repeat(temp));
            }
        }
        return answer.toString();
    }
    public void checkExceptions(String[] str) throws IOException {
        if (str.length != 3) {
            throw new IOException();
        }
        if ((!romanNumbers.containsKey(str[0]) && romanNumbers.containsKey(str[2])) || romanNumbers.containsKey(str[0]) && !romanNumbers.containsKey(str[2])) {
            throw new IOException();
        }
        if (!romanNumbers.containsKey(str[0]) && !romanNumbers.containsKey(str[2])) {
            try {
                if (Integer.parseInt(str[0]) > 10 || Integer.parseInt(str[2]) > 10) {
                    throw new IOException();
                }
            } catch (NumberFormatException n) {
                throw new IOException();
            }
        }
    }

    public void Start () throws IOException {
        String[] str = formatStr();
        checkExceptions(str);

        int num1;
        int num2;
        int result = 0;
        boolean isRoman = false;

        if ((romanNumbers.containsKey(str[0]) && romanNumbers.containsKey(str[2]))) {
            num1 = romanNumbers.get(str[0]);
            num2 = romanNumbers.get(str[2]);
            isRoman = true;
        }
        else {
            num1 = Integer.parseInt(str[0]);
            num2 = Integer.parseInt(str[2]);
        }

        switch (str[1]) {
            case ("+") -> result = num1 + num2;
            case ("-") -> result = num1 - num2;
            case ("/") -> result = num1 / num2;
            case ("*") -> result = num1 * num2;
        }

        if(isRoman){
            if (result < 0) {
                throw new IOException();
            }
            String romanResult = arabToRoman(result);
            System.out.println(romanResult);
        }
        else {
            System.out.println(result);
        }
    }
}