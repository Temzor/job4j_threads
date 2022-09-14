package codewarssevenkyu.mapexample;

import java.util.HashMap;

public class MapStart {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put(new Key("Dima"), 20);
        System.out.println((int) "Dima".charAt(0));
        System.out.println((int) "Dima".charAt(0) & (16 - 1));


    }
}
