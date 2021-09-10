import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class CalculateTest {

    @Test
    public void shouldAddNumbers(){
        int a = 20;
        int b = 30;

        int result = new Calculator().add(a, b);

        int expect = 50;
        assertThat(result).isEqualTo(expect);
    }

    class Calculator {
        int add(int a, int b) {
            return a + b;
        }
    }
}
