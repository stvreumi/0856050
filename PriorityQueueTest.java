import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;


public class PriorityQueueTest {
    /*
        Parameterization
     */

    // https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-sources-MethodSource
    @ParameterizedTest
    @MethodSource("testPriorityQueueInputProvider")
    void testPriorityQueuePoll(String typeName, List randomInput, List expectedOutput) {
        List priorityQueueResultOutput;
        switch (typeName) {
            case "str":
                priorityQueueResultOutput = PriorityQueueTest.<String>priorityQueuePollResult(randomInput);
                break;
            case "int":
                priorityQueueResultOutput = PriorityQueueTest.<Integer>priorityQueuePollResult(randomInput);
                break;
            case "float":
                priorityQueueResultOutput = PriorityQueueTest.<Float>priorityQueuePollResult(randomInput);
                break;
            case "long":
                priorityQueueResultOutput = PriorityQueueTest.<Long>priorityQueuePollResult(randomInput);
                break;
            case "char":
                priorityQueueResultOutput = PriorityQueueTest.<Character>priorityQueuePollResult(randomInput);
                break;
            default:
                priorityQueueResultOutput = new ArrayList();
        }
        assertEquals(expectedOutput, priorityQueueResultOutput);
        //assertTrue(expectedOutput.equals(priorityQueueResultOutput));
    }

    // generic method
    // https://ethan-imagination.blogspot.com/2018/11/javase-gettingstarted-017.html
    static <T> List<T> priorityQueuePollResult(List<T> input) {
        PriorityQueue<T> pQueue = new PriorityQueue<T>();
        for(T i: input) {
            pQueue.add(i);
        }
        List<T> pollOutList = new ArrayList<T>();
        while(pQueue.size() > 0) {
            pollOutList.add(pQueue.poll());
        }
        return pollOutList;
    }

    static Stream<Arguments> testPriorityQueueInputProvider() {
        return Stream.of(
            Arguments.arguments(
                "str",
                Arrays.asList("banana", "lemon", "apple", "peach"),
                Arrays.asList("apple", "banana", "lemon", "peach")
            ),
            Arguments.arguments(
                "int",
                Arrays.asList(56, 7 ,1, 82, 9, -3, -20),
                Arrays.asList(-20, -3, 1, 7, 9, 56, 82)
            ),
            Arguments.arguments(
                "float",
                Arrays.asList(-1.3, 5.6, 0.373, 99.832),
                Arrays.asList(-1.3, 0.373, 5.6, 99.832)
            ),
            Arguments.arguments(
                "long",
                Arrays.asList(-923487745L, 283940L, 199816L, 29399L),
                Arrays.asList(-923487745L, 29399L, 199816L, 283940L)
            ),
            Arguments.arguments(
                "char",
                Arrays.asList('t', 'e', 's', 't', 'i', 'n', 'g'),
                Arrays.asList('e', 'g', 'i', 'n', 's', 't', 't')
            )
        );
    }

    /*
        Exception
     */
    @Test
    void testIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(-1);
        });
    }
    @Test
    void testNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {
            PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
            Iterator iter = pQueue.iterator();
            while (iter.hasNext()) {
                iter.next();
            }
            iter.next();
        });
    }
    @Test
    void testClassCastException() {
        assertThrows(ClassCastException.class, () -> {
           PriorityQueue pQueue = new PriorityQueue();
           pQueue.add(-1);
           pQueue.add("software testing");
        });
    }

}
