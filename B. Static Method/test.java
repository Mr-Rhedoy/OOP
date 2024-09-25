public class test {
    public static void main (String[] args)
    {
       student s1= new student(); // non static method;
       s1.display1();
       student.display2(); //static method;
    }
}
