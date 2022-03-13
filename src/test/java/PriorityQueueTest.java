import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {
    static Stream<Arguments> streamProvider(){
        return Stream.of(
                Arguments.of(new int[]{7, 5, 3}, new int[]{3, 5, 7}),
                Arguments.of(new int[]{-5, -10, 8, 4}, new int[]{-10, -5, 4, 8}),
                Arguments.of(new int[]{-3, 6, -2, 1, 0}, new int[]{-3, -2, 0, 1, 6}),
                Arguments.of(new int[]{-100, 5, 1}, new int[]{-100, 1, 5}),
                Arguments.of(new int[]{19, 20, 3, 0}, new int[]{3, 0, 19, 20})
        );
    }

    @ParameterizedTest(name="#{index} - Test with Argument={0},{1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array){
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        int index = 0; // for cnt index
        Integer s; // for PriorityQueue.poll()
        int[] result = new int[random_array.length];

        for(int i = 0; i < random_array.length; i++){
            test.add(random_array[i]);
        }

        while (test.size() > 0) {
            s = test.poll();
            result[index] = s.intValue();
            index++;
        }
        assertArrayEquals(correct_array, result);
    }

    @Test
    public void whenExceptionThrown_thenOfferEisNull() {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        Exception exception = assertThrows(NullPointerException.class, () -> {
            q.offer(null);
        });
    }

    @Test
    public void whenExceptionThrown_thenInitialCapacityNotGreaterThanOne() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue<Integer> q = new PriorityQueue<Integer>(-10);
        });
    }

    @Test
    public void whenExceptionThrown_thenNotElementCanRemove() {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(10);
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            q.remove();
        });
    }
}