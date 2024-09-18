public class test2 {
    public static void main(String[]args)
    {
        // tecnique 1
        Returningavalue obj1= new Returningavalue();
        int result= obj1.square(5);
        System.out.println("Square is = "+result);

        System.out.println("\n");

        //tecnique 2
        Returningavalue obj2= new Returningavalue();
        System.out.println("Square is = "+obj2.square(5));
    }
}
