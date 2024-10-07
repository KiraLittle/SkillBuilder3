<div style="text-align:center;"><img src="src/main/resources/pemacs-logo.png"></div>
# Skill Builder 3 - Defining Classes and Methods

## Learning Outcomes

By the end of this activity, a student should be able to:

1. Implement a simple Java class
2. Use other classes to accomplish more complex tasks
3. Implement accessor and mutator methods
4. Follow a test-driven design methodology.
4. Design objects that interact with each other.

## The Grok Coalition

Imagine you are writing a video game that has a character called a Grok. The Grok is a simple creature that can eat power pills and increase its power level by the amount of power available in the power pill. If the Grok takes a hit, then its power level is reduced by 5 points.

## The PowerPill Class

In the previous Skill Builder, you implemented the `PowerPill` class.  To review,
a power pill is an object that is available for the Grok to ingest.  Once taken, it transfers its power to the Grok.  A client can create different power pills with varying levels of power and names.  The template for the PowerPill class as should have been implemented in the previous Skill Builder is provided below without the implementation.

```java
public class PowerPill
{
    private static final int DEFAULT_POWER = 10;
    
    // instance variables
    private int powerSupply;
    private String name;
    
    // constructors
    /*
     * Initializes this power pill to a default power value and
     * and sets the name of the pill to name.
     * @param name name of this power pill.
     */
    public PowerPill(String name);
    
    /*
     * Initializes this power pill to the value of power  and
     * and sets the name of the pill to name.
     * @param name name of this power pill
     * @param power power level of this power pill.
     */
    public PowerPill(String name, int power);
    
    // accessor methods
    
    /*
     * Returns the power provided by this pill.
     * @return the power provided by this power pill
     */
    public int getPower();
    
    /*
     * Returns the name of this power pill.
     * @return the name of this power pill
     */
    public String getName();
    
    // mutator methods
    
    /*
     * Sets the power value of this power pill.
     * @param power power value to set for this power pill.
     */
    public void setPower(int power);
    
    /*
     * Set the name of this power pill.
     * @param name the name to give this power pill.
     */
    public void setName(String name);
}

```

## Grok Class

Groks are bad actors in a game. They can ingest a PowerPill to replenish their energy, making them a challenge to kill. When Groks take a power pill, they take the power level of the pill. When Groks get hit, they lose 5 points of power. You are provided the following class and will need to complete it based on the documentation provided.

```java
public class Grok
{
    // TODO - complete this class per instructions below.
}
```

## Required Activities

You are to implement the following in the `Grok` class.

1. Add a ***static*** integer field called `DEFAULT_POWER_LEVEL` and set it to 50. (**NOTE: static variables can be initialized outside of constructors, but NOT INSTANCE variables!**)
2. Add an integer field called `powerLevel`.
3. Add a default constructor.  Add the javadoc comment below before the constructor name.
<pre>
	/**
     \* Initializes a Grok object to the default power level of 50.
     */
</pre>
5. Add a value constructor with a single parameter that is an integer called `powerLevel`.  Add the javadoc comment below before the constructor.

	```
	  /**
	   *
       * Initializes a Grok object to power powerLevel
       */
	```
	
6. Add a getter method with the following javadoc comment

   ```
    /*
     * Returns the power level of this Grok.
     * @return returns the power level of this Grok
     */
   ```
   
7. Add a setter method with the following javadoc comment
   
   ```
    /*
     * Sets the power level of this Grok.
     * @param powerLevel the power value to set for this Grok.
     */

   ```

8. Add a mutator method called `takePowerPill` with the following javadoc comment.
   
   ```
    \*
     * Set the power level of this Grok to the power level of
     * the pill.
     * @param pill The PowerPill that the this Grok.  The power
     * of the pill is added to the power level of this Grok.
     */
   ```
   
9. Add a mutator method called `tookHit` with the following javadoc comment

   ```
     /*
     * Invoked when this Grok takes a hit.  The power level of
     * this Grok is reduced by 5.
     */
   ```

## How To Use the Classes

An example of how the Classes may be used is,

```java
PowerPill bluePill = new PowerPill("Blue");
PowerPill redPill = new PowerPill("Red",40);

Grok ghostlyGrok = new Grok();
ghostlyGrok.takePowerPill(bluePill);
ghostlyGrok.tookHit();
ghostlyGrok.tookHit();
ghostlyGrok.takePowerPill(redPill);
```

## Implementing and Testing the Classes

In this Skill Builder, you are being asked to implement the methods and cosnstructors as instructed in the section **Required Activities**.  It is recommended that you add empty methods first, run the test, and note that there are no syntax errors.  If a method returns a value, then return a default value that will make the test fail.  For example, if a method returns an integer, then implement one line, `return 0;`.  This will eliminate the syntax error in the test class, but will make the initial test fail. Empty methods or unimplemented methods  that return an incorrect values are called **skeleton methods**.

*Once the skeleton methods have been added and no syntax errors exist, you can begin implementing the methods one by one and make sure each implementation passes the test before moving on to the next method.*

In general, getting used to the idea of using the symbolic debugger to locate the source of an error is an excellent
overall strategy.  So, use it on every method that fails the test!


<span style="font-size:2em;color:green;">Happy Coding!</span>