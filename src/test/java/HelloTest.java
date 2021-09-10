import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;


public class HelloTest {
    @Test
    public void testFail(){
         Assertions.assertTrue(true, "test");
    }

    @Test
    @Disabled
    public void testException(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->{
            new ArrayList<String>().get(0);
        });
    }

    @RepeatedTest(10)
    @Disabled
    public void testRepeat(){
        Assertions.assertTrue (new Random().nextInt() > 0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"racecar", "radar", "able was I ere I saw elba"})
    public void testpalindromes(String candidate){
        Assertions.assertTrue(!candidate.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"", "   ", "\t", "\n"})
    public void testEmpty(String text){
        Assertions.assertTrue(text == null || text.trim().isEmpty());
    }

    @ParameterizedTest
    @EnumSource(ChronoUnit.class)
    void testWithEnum(TemporalUnit unti){
        Assertions.assertNotNull(unti);
    }

    @Test
    void shouldCheckAll(){
        ArrayList<Integer> integers = new ArrayList<Integer>(Arrays.asList(1,2,3,4)) ;

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, integers.get(0)),
                () -> Assertions.assertEquals(2, integers.get(1))
        );
    }

    @Test
    @DisplayName("shoul ")
    void shouldOnlyRunTheTestIfSomeCriteriaAreMet(){
        Assumptions.assumeTrue(false);
        assertEquals(1,1);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void shouldGet(int j){
        assertTrue(j > 0);
    }

}
