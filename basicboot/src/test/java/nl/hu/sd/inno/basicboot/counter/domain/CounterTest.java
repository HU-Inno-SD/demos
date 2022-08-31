package nl.hu.sd.inno.basicboot.counter.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Singletons zijn altijd een beetje rot om te testen... Maar vooruit
public class CounterTest {

    @BeforeEach
    public void reset() {
        Counter.getInstance().reset();
    }

    @Test
    public void counterStartsAtZero() {
        assertEquals(0, Counter.getInstance().getCurrentValue());
    }

    @ParameterizedTest
    @MethodSource("someNumbers")
    public void incrementIncreasesByOne(int nr) {
        for (int i = 0; i < nr; i++) {
            Counter.getInstance().increment();
        }

        assertEquals(nr, Counter.getInstance().getCurrentValue());
    }

    public static Stream<Integer> someNumbers() {
        return Stream.of(10, 23, 101, 987);
    }
}
