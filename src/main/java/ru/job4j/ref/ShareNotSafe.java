package ru.job4j.ref;

public class ShareNotSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCacheNotSafe userCacheNotSafe = new UserCacheNotSafe();
        User user = User.of("name");
        userCacheNotSafe.add(user);
        Thread first = new Thread(
                () -> {
                    user.setName("rename");
                }
        );
        first.start();
        first.join();
        System.out.println(userCacheNotSafe.findById(1).getName());
    }
}