package codewarssevenkyu;

import java.util.Arrays;
import java.util.LinkedList;

public class Pendulum {
    public static void main(String[] args) {
        final int[] ddd = {7, 3, 1, 2, 4};
        pendulum(ddd);
    }

    public static int[] pendulum(final int[] values) {

        Arrays.sort(values);

        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < values.length; i++) {
            if (i % 2 == 0) {
                list.addFirst(values[i]);
            } else {
                list.addLast(values[i]);
            }
        }
        return Arrays.stream(list.toArray()).mapToInt(x -> (int) x).toArray();

    }
}
