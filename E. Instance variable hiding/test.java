/* Problem-1

To solve this problem knowledge required about class, objects, variables, data types, methods, constructor

Create a class called Box that include three pieces of information as instance variables-height,
width and depth (type double) of two boxes. Your class should have a constructor and initializes
the three Instance variables. Provide a method displayVol that display the volume of two boxes. Suppose,
the values of instance variables for the first box's are (10,10,10) and second box's are (20,30,10).
 Write a test application named BoxVolume that demonstrate class Box's capabilities. */

public class test {
    public static void main(String[] args)
    {
        Box b1= new Box(10,10,10);
        Box b2= new Box(20,30,10);
        b1.displayvol();
        b2.displayvol();
    }
}
