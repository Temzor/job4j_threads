package ru.job4j.array;

public class JavaNameValidator {

    public static boolean isNameValid(String name) {
        if (name.isEmpty() || !isLowerLatinLetter(name.codePointAt(0))) {
            return false;
        }
        for (int index = 1; index < name.length(); index++) {
            int code = name.codePointAt(index);
            if (!(isSpecialSymbol(code) || isUpperLatinLetter(code) || isLowerLatinLetter(code) || isDigit(code))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSpecialSymbol(int code) {
        return code == 36 || code == 95;
    }

    public static boolean isUpperLatinLetter(int code) {
        return code >= 65 && code <= 90;
    }

    public static boolean isLowerLatinLetter(int code) {
        return code >= 95 && code <= 122;
    }

    public static boolean isDigit(int code) {
        return code >= 48 && code <= 57;
    }
}
