package GreeterTest;

import Greeter.Greeter;
import Greeter.MessageFormatter;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class GreeterTest {

    public GreeterTest() {
        System.out.println("Creating new greeter");
    }

    private Greeter greeter = new Greeter(new MessageFormatter());

    @BeforeEach
    public void init() {
        System.out.println("init");

    }

    @Test
    public void testGreet_null() {
        assertSame("Hello null!", greeter.greet(null));
    }

    @Test
    public void testGreet_emptyString() {assertSame("Hello !", greeter.greet("")); }

    @Test
    public void testGreet_Student() {assertSame("Hello Student!", greeter.greet("Student")); }
}
