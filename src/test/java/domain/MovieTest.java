package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vlad on 25.03.2017.
 */
public class MovieTest {
    Movie m1;
    Movie m2;

    @Before
    public void setUp() throws Exception {
        m1 = new Movie(1L, "My movie", "1999", "www.mymovie.org");
        m2 = new Movie(2L, "My movie", "1999", "www.mymovie.org");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void equals() throws Exception {
        assertTrue(m1.equals(m2));
        m2.setWebsite("www.mymovie.tv");
        assertFalse(m1.equals(m2));
        m2.setWebsite("www.mymovie.org");
        assertTrue(m1.equals(m2));
        m1.setYear("2000");
        assertFalse(m1.equals(m2));
    }

}