package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vlad on 25.03.2017.
 */
public class ActorTest {
    private Actor a1;
    private Actor a2;

    @Before
    public void setUp() throws Exception {
        a1 = new Actor(
                1L,
                "John",
                "Smith",
                "22-02-1970",
                "american",
                "New York",
                "1.75m",
                "78kg",
                "john@smith.com",
                "0745254896"
        );

        a2 = new Actor(
                2L,
                "John",
                "Smith",
                "22-02-1970",
                "american",
                "New York",
                "1.70m",
                "70kg",
                "john.smith@gmail.com",
                "0714258965"
        );
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void equals() throws Exception {
        assertTrue(a1.equals(a2));
        a1.setFirstName("Bill");
        assertFalse(a1.equals(a2));
    }

}