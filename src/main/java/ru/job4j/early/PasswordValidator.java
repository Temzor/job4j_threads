package ru.job4j.early;

public class PasswordValidator {

    public static String validate(String password) {

        String[] usuallyPasswords = new String[]{"qwerty", "password", "admin", "user", "12345"};

        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }

        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }

        if (!isUpperCaseSymbol(password)) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }

        if (!isLowerCaseSymbol(password)) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }

        if (!isDigitCaseSymbol(password)) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }

        if (!isSpecialCaseSymbol(password)) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }

        if (isUsuallyPassword(usuallyPasswords, password)) {
            throw new IllegalArgumentException("Password shouldn't contain substrings: qwerty, 12345, password, admin, user");
        }

        return password;
    }

    private static boolean isUpperCaseSymbol(String password) {
        for (char symbol : password.toCharArray()) {
            if (Character.isUpperCase(symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isLowerCaseSymbol(String password) {
        for (char symbol : password.toCharArray()) {
            if (Character.isLowerCase(symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDigitCaseSymbol(String password) {
        for (char symbol : password.toCharArray()) {
            if (Character.isDigit(symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSpecialCaseSymbol(String password) {
        for (char symbol : password.toCharArray()) {
            if (!Character.isLetterOrDigit(symbol) && !Character.isWhitespace(symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isUsuallyPassword(String[] usuallyPassword, String password) {
        for (String word : usuallyPassword) {
            if (password.toLowerCase().contains(word)) {
                return true;
            }
        }
        return false;
    }
}
