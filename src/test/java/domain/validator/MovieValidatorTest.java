package domain.validator;

import domain.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vlad on 25.03.2017.
 */
public class MovieValidatorTest {
    private Movie m;
    private Validator<Movie> mv = new MovieValidator();

    @Before
    public void setUp() throws Exception {
        m = new Movie(1L, "My movie", "1999", "www.mymovie.org");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void validate() throws Exception {
        try {
            mv.validate(m);
            assert true;
        } catch (ValidatorException e) {
            assert false;
        }
    }

    @Test
    public void validateYear() throws Exception {
        m.setYear("1820");
        try {
            mv.validate(m);
            assert false;
        } catch (ValidatorException e) {
            assert true;
        }

        m.setYear("1956");
        try {
            mv.validate(m);
            assert true;
        } catch (ValidatorException e) {
            assert false;
        }

        m.setYear("400 i.Hr.");
        try {
            mv.validate(m);
            assert false;
        } catch (ValidatorException e) {
            assert true;
        }

        m.setYear("2010-");
        try {
            mv.validate(m);
            assert false;
        } catch (ValidatorException e) {
            assert true;
        }
    }

    @Test
    public void ValidateWebsite() throws Exception {
        m.setWebsite("www.example.com");
        try {
            mv.validate(m);
            assert true;
        } catch (ValidatorException e) {
            assert false;
        }

        m.setWebsite("http://www.example.com");
        try {
            mv.validate(m);
            assert true;
        } catch (ValidatorException e) {
            assert false;
        }

        m.setWebsite("https://www.example.com");
        try {
            mv.validate(m);
            assert true;
        } catch (ValidatorException e) {
            assert false;
        }

        m.setWebsite("www");
        try {
            mv.validate(m);
            assert false;
        } catch (ValidatorException e) {
            assert true;
        }

        m.setWebsite("ftp://www.example.com");
        try {
            mv.validate(m);
            assert false;
        } catch (ValidatorException e) {
            assert true;
        }

        m.setWebsite("adresa.de.email@example.com");
        try {
            mv.validate(m);
            assert false;
        } catch (ValidatorException e) {
            assert true;
        }
    }
}