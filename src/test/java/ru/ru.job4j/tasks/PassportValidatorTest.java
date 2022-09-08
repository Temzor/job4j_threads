package tasks;

import org.junit.jupiter.api.Test;
import ru.job4j.tasks.PasswordValidator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PassportValidatorTest {

    @Test
    public void checkNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PasswordValidator.validate(null));
        assertThat(exception.getMessage()).isEqualTo("Password is NULL");
    }

    @Test
    public void checkLengthSmall() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PasswordValidator.validate("123"));
        assertThat(exception.getMessage()).isEqualTo("Password is small(< 7)  ro large (> 32)");
    }


    @Test
    public void checkLengthLarge() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PasswordValidator.validate("12345678910111213141516171819202122223242526"));
        assertThat(exception.getMessage()).isEqualTo("Password is small(< 7)  ro large (> 32)");
    }


    @Test
    public void checkUpperChar() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PasswordValidator.validate("kasmasdas1234"));
        assertThat(exception.getMessage()).isEqualTo("Password don't  have upper char");
    }

    @Test
    public void checkLowerChar() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PasswordValidator.validate("GOGOGOGO"));
        assertThat(exception.getMessage()).isEqualTo("Password don't  have lower char");
    }


    @Test
    public void checkDigit() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PasswordValidator.validate("KASMASDASqwer"));
        assertThat(exception.getMessage()).isEqualTo("Password don't have digit");
    }
}