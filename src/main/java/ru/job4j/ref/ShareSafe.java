package ru.job4j.ref;

public class ShareSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCacheSafe userCacheSafe = new UserCacheSafe();
        User user = User.of("name");
        userCacheSafe.add(user);
        Thread firstThread = new Thread(
                () -> user.setName("rename")
        );
        firstThread.start();
        firstThread.join();
        System.out.println(userCacheSafe.findById(1).getName());
        System.out.println("-------------------------------------------");

        User userFirst = User.of("email");
        User userSecond = User.of("surname");
        userCacheSafe.add(userFirst);
        userCacheSafe.add(userSecond);
        Thread secondThread = new Thread(
                () -> {
                    userFirst.setId(0);
                    userFirst.setName("reEmailName");

                    userSecond.setId(1);
                    userSecond.setName("reSurname");
                }
        );
        secondThread.start();
        secondThread.join();
        System.out.println(userCacheSafe.findAll());
        System.out.println(userCacheSafe.findAll().size());
    }
}