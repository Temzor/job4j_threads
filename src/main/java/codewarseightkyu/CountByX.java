package codewarseightkyu;

public class CountByX {
    public int[] countBy(int x, int y) {
        int start = x;
        int[] ints = new int[y];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = start;
            start += x;
        }

        return ints;
    }
}
