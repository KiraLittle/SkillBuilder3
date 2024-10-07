import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.lang.reflect.*;

class GrokTest {
    private static Field[] field;
    private static Constructor<?>[] constructors;
    private static Random rand;
    private static final String[] color = {"Blue", "Red", "Pink", "Purple", "Green"};


    @BeforeAll
    static void beforeAll() {
        rand = new Random();

        field = Grok.class.getDeclaredFields();
        constructors = Grok.class.getDeclaredConstructors();
    }

    @Test
    @DisplayName("[1] test testDefinedStaticField")
    void testDefinedStaticField(){
        boolean isDone = false;
        for(int index=0; index < field.length && !isDone; index++){
            if (field[index].getName().equals("DEFAULT_POWER_LEVEL")){
                isDone = true;
                assertTrue(Modifier.isFinal(field[index].getModifiers()),"DEFAULT_POWER_LEVEL should be final!");
                assertTrue(Modifier.isStatic(field[index].getModifiers()),"DEFAULT_POWER_LEVEL should be static!");
                assertEquals(field[index].getType().getTypeName(),"int");
                try {
                    int expectedIntValue = 50;
                    field[index].setAccessible(true);
                    int actualIntValue = field[index].getInt(null);
                    assertEquals(expectedIntValue, actualIntValue);
                } catch (IllegalAccessException iae){
                    assertTrue(iae.getMessage().contains("Attempt to get int"),iae.getMessage());
                }
                assertTrue(Modifier.isStatic(field[index].getModifiers()),"DEFAULT_POWER_LEVEL should be private!");
                assertTrue(Modifier.isPrivate(field[index].getModifiers()),"DEFAULT_POWER_LEVEL should be private!");
            }
        }
        assertTrue(isDone,"Static integer field, DEFAULT_POWER_LEVEL, not defined!");
    }

    @Test
    @DisplayName("[1] test testFieldDeclared_powerLevel")
    void testFieldDeclared_powerLevel(){
        boolean isDone = false;
        for(int index=0; index < field.length && !isDone; index++){
            if (field[index].getName().equals("powerLevel")){
                isDone = true;
                assertEquals(field[index].getType().getTypeName(),"int");
            }
            assertTrue(Modifier.isPrivate(field[index].getModifiers()),"Instance variable powerLevel should be private!");
        }
        assertTrue(isDone,"Integer field, powerLevel, not declared!");
    }

    @Test
    @DisplayName("[1] test testDeclaredConstructor_1")
    void testDeclaredConstructor_1(){
        boolean isDone = false;
        for(int index=0; index < constructors.length && !isDone; index++){
            assertFalse(constructors[index].getParameterCount() > 1,"No constructor should have more than 1 parameter!");
            if (constructors[index].getParameterCount() == 0){
                isDone = true;
            }
        }
        assertTrue(isDone,"Default constructor is not defined!");

        Grok g = new Grok();
        try {
            Field fp = g.getClass().getDeclaredField("powerLevel");
            fp.setAccessible(true);
            assertEquals(50, fp.getInt(g), "In default constructor: powerLevel is not initialized properly!");
        } catch (NoSuchFieldException | IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("[1] test testDeclaredConstructor_2")
    void testDeclaredConstructor_2(){
        boolean isDone = false;
        for(int index=0; index < constructors.length && !isDone; index++){
            assertFalse(constructors[index].getParameterCount() > 1,"No constructor should have more than 1 parameter!");
            if (constructors[index].getParameterCount() == 1){
                isDone = true;
                Parameter[] p = constructors[index].getParameters();
                assertEquals("int", p[0].getType().getTypeName(), "Constructor parameter type should be an integer!");
            }
        }
        assertTrue(isDone,"A value constructor with a string parameter called name is not defined!");

        int power = rand.nextInt(50)+60;
        Grok g = new Grok(power);
        try {
            Field fp = g.getClass().getDeclaredField("powerLevel");
            fp.setAccessible(true);
            assertEquals(power, fp.getInt(g), "In value constructor: powerLevel is not initialized properly!");
        } catch (NoSuchFieldException | IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }


    @Test
    @DisplayName("[1] test getPowerLevel")
    void getPowerLevel() {
        for(int count = 0; count < 3; count++){
            int expected = rand.nextInt(300);
            Grok aGrok = new Grok(expected);
            int actual = aGrok.getPowerLevel();
            assertEquals(expected,actual,"Expected " + expected + " got "+ actual);
        }
    }

    @Test
    @DisplayName("[1] test setPowerLevel")
    void setPowerLevel() {
        for(int count = 0; count < 3; count++){
            int expected = rand.nextInt(300);
            Grok aGrok = new Grok();
            aGrok.setPowerLevel(expected);
            try {
                Field fp = aGrok.getClass().getDeclaredField("powerLevel");
                fp.setAccessible(true);
                int actual = fp.getInt(aGrok);
                assertEquals(expected, actual, "Expected " + expected + " got " + actual);
            } catch (IllegalAccessException | NoSuchFieldException e){
                System.out.println(e.getMessage());
            }

        }
    }

    @Test
    @DisplayName("[2] test takePowerPill")
    void takePowerPill() {
        for(int index = 0; index < 3; index++){
            int power = rand.nextInt(200);
            PowerPill aPill = new PowerPill("Blue",power);
            Grok aGrok = new Grok();
            try {
                Field fp = aGrok.getClass().getDeclaredField("powerLevel");
                fp.setAccessible(true);
                int expected = fp.getInt(aGrok)+power;
                aGrok.takePowerPill(aPill);
                int actual = fp.getInt(aGrok);
                assertEquals(expected,actual,"Expected " + expected + " got "+ actual);
            } catch (IllegalAccessException | NoSuchFieldException e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Test
    @DisplayName("[2] test tookHit")
    void tookHit() {
        int power = rand.nextInt(800)+10;
        //int expected = (power % 5);
        int expected = power >  0? power - 5:0;
        Grok aGrok = new Grok(power);
        try {
            Field fp = aGrok.getClass().getDeclaredField("powerLevel");
            fp.setAccessible(true);
            aGrok.tookHit();
            int actual = fp.getInt(aGrok);
            assertEquals(expected,actual,"Expected " + expected + " got "+ actual);
        } catch (IllegalAccessException | NoSuchFieldException e){
            System.out.println(e.getMessage());
        }
    }
}