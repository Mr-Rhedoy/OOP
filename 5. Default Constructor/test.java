public class test {
    public static void main(String[]args)
    {
        //parametrized Constructor
        Teacher teacher1= new Teacher("Rhedoy","Male","01303517139",22);
        teacher1.displayInformation();

        Teacher teacher2= new Teacher("Onti","Female","01761198246",22);
        teacher2.displayInformation();

        //Default Constructor
        Teacher teacher3=new Teacher();
        teacher3.displayInformation();
    }
}