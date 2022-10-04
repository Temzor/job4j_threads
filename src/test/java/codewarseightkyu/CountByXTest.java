package codewarseightkyu;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CountByXTest {
    @Test
    public void fixedTests1() {
        CountByX countByX = new CountByX();
        int[] result = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertThat(result).hasSize(10);
        assertThat(result).contains(countByX.countBy(1, 10));
    }

    @Test
    public void fixedTests2() {
        CountByX countByX = new CountByX();
        int[] result = new int[] {3, 6, 9, 12, 15, 18, 21};
        assertThat(result).hasSize(7);
        assertThat(result).contains(countByX.countBy(3, 7));
    }

    @Test
    public void fixedTests3() {
        CountByX countByX = new CountByX();
        int[] result = new int[] {50, 100, 150, 200, 250};
        assertThat(result).hasSize(5);
        assertThat(result).contains(countByX.countBy(50, 5));
    }

    @Test
    public void fixedTests4() {
        CountByX countByX = new CountByX();
        int[] result = new int[] {100, 200, 300, 400, 500, 600};
        assertThat(result).hasSize(6);
        assertThat(result).contains(countByX.countBy(100, 6));
    }
}