import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("** Инструкция:" + "\n" + "* Поддерживаются арабские и римские числа до 10 либо до X" + "\n" + "* Педдерживаются седующие выражения: '+', '-', '*' и '/'" +
                "\n" + "* Числа должны быть введены в одинаковой системе, арабской либо римской" + "\n" + "* Вводите только целые числа" + "\n" + "* При делении выводится целое число"
                + "\n" + "  Можете начинать :)");
        System.out.println("--------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическое выражение:");
        String input = scanner.nextLine();// считываем данные от пользователя
        String result = calc(input);// вызываем метод calc
        System.out.println("Результат: " + result);// вывод результата
    }
    public static String calc (String input) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        String[] operands = input.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Должно быть два операнда!");
        oper = detectOperation(input);
        if (oper == null) throw new Exception("Неподдерживаемая математическая операция!");
        // если оба числа римские то конвертируем оба числа в арабские
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }
        // если оба числа арабские то продолжаем вычисления
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        // если одно число римско, а ругое арабское выводим ошибку
        else {
            throw new Exception("Вы ввели числа из разных систем счисления!");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть меньше 10");
        }
        int arabian = calc(num1, num2, oper);
        if (isRoman) {
            //если римское число меньше либо равно нулю то ошибка
            if (arabian <= 0) {
                throw new Exception("В римской системе нет орицательных чисел!");
            }
            //конвертируем результат из арабского в римское
            result = Roman.convertToRoman(arabian);
        } else {
            //конвертируем арабское число в тип String
            result = String.valueOf(arabian);
        }
        return result;
    }

    static String detectOperation (String expression){
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc ( int a, int b, String oper){
        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }
}


class Roman {
    static String[] romanArray = new String[] {
            "0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV",
            "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI",
            "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII",
            "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII",
            "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII",
            "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII",
            "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };
    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }
    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }

}