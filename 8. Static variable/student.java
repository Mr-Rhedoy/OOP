public class student {
    String name; // intenious variable;
    int id;
    static String university_name="Daffodil"; //static varible; (it is fixed)

    student(String n, int i)
    {
        name=n;
        id=i;
    }
    void displayinformation()
    {
        System.out.println("Name = "+name);
        System.out.println("Id = "+id);
        System.out.println("University = "+university_name);
        System.out.println("\n");
    }
}

