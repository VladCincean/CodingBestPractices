package domain.validator;

import domain.Actor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vlad on 25.03.2017.
 */
public class ActorValidatorTest {
    Actor a;
    Validator<Actor> av = new ActorValidator();

    @Before
    public void setUp() throws Exception {
        a = new Actor(
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
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void validate() throws Exception {
        try {
            av.validate(a);
            assert true;
        } catch (ValidatorException e) {
            assert false;
        }
    }

    @Test
    public void validateBirthDate() throws Exception {
        a.setBirthDate("29-02-1985");
        try {
            av.validate(a);
            assert false;
        } catch (ValidatorException e) {
            assert true;
        }

        a.setBirthDate("29-02-1984");
        try {
            av.validate(a);
            assert true;
        } catch (ValidatorException e) {
            assert false;
        }
    }

    @Test
    public void validateCity() throws Exception {
        a.setCity("Cluj-Napoca");
        try {
            av.validate(a);
            assert true;
        } catch (ValidatorException e) {
            assert false;
        }

        a.setCity("ooo---oooo");
        try {
            av.validate(a);
            assert false;
        } catch (ValidatorException e) {
            assert true;
        }
    }

    @Test
    public void validateWeight() throws Exception {
        a.setWeight("9kg");
        try {
            av.validate(a);
            assert false;
        } catch (ValidatorException e) {
            assert true;
        }

        a.setWeight("120kg");
        try {
            av.validate(a);
            assert true;
        } catch (ValidatorException e) {
            assert false;
        }
    }

    @Test
    public void validateHeight() throws Exception {
        a.setHeight("2m");
        try {
            av.validate(a);
            assert true;
        } catch (ValidatorException e) {
            assert false;
        }

        a.setHeight("2.01m");
        try {
            av.validate(a);
            assert true;
        } catch (ValidatorException e) {
            assert false;
        }

        a.setHeight("1.875m");
        try {
            av.validate(a);
            assert false;
        } catch (ValidatorException e) {
            assert true;
        }
    }

    @Test
    public void validateEmail() throws Exception {
        a.setEmail("troll@@@@@invalid.com");
        try {
            av.validate(a);
            assert false;
        } catch (ValidatorException e) {
            assert true;
        }

        a.setEmail("not-an-email");
        try {
            av.validate(a);
            assert false;
        } catch (ValidatorException e) {
            assert true;
        }

        a.setEmail("a.b.c.d.e.f@g.h.i.j.k.com");
        try {
            av.validate(a);
            assert true;
        } catch (ValidatorException e) {
            assert false;
        }
    }

    @Test
    public void validatePhoneNumber() throws Exception {
        a.setPhoneNumber("0725612548");
        try {
            av.validate(a);
            assert true;
        } catch (ValidatorException e) {
            assert false;
        }

        a.setPhoneNumber("0425612548");
        try {
            av.validate(a);
            assert false;
        } catch (ValidatorException e) {
            assert true;
        }

        a.setPhoneNumber("1725612548");
        try {
            av.validate(a);
            assert false;
        } catch (ValidatorException e) {
            assert true;
        }
        a.setPhoneNumber("0800800800");
        try {
            av.validate(a);
            assert true;
        } catch (ValidatorException e) {
            assert false;
        }

    }

}