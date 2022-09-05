package ru.job4j.tasks;

    public class PasswordValidator {

        public static void validate(String password) {
            if (password == null) {
                throw new IllegalArgumentException("Password is NULL");
            }

            if (password.length() <= 7 || password.length() >= 33) {
                throw new IllegalArgumentException("Password is small(< 7)  ro large (> 32)");
            }

            if (!password.matches("[A-Z]+")) {
                throw new IllegalArgumentException("Password don't  have upper char");
            }

            if (!password.matches("[a-z]+")) {
                throw new IllegalArgumentException("Password don't  have lower char");
            }

            if (!password.matches("[0-9]")) {
                throw new IllegalArgumentException("Password don't have digit");
            }

            if (!password.matches("[!@#$%^&*()_]*")) {
                throw new IllegalArgumentException("Password don't have specified symbols");
            }

            if (!password.toLowerCase().matches("[qwerty]*")
                    || !password.toLowerCase().matches("[12345]*")
                    || !password.toLowerCase().matches("[password]*")
                    || !password.toLowerCase().matches("[admin]*")
                    || !password.toLowerCase().matches("[user]*")) {
                throw new IllegalArgumentException("Password have standard symbols");
            }

        }

    }

