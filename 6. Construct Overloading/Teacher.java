import java.sql.SQLOutput;

public class Teacher
{
    String name, gender, phone;
    int age;
    //default constructor
    Teacher()
    {
        System.out.println("NO Information");
        System.out.println("\n");
    }
    //parameter Constructor;
    Teacher(String n, String g)
    {
        name=n;
        gender=g;
    }
    Teacher(String n, String g, String p, int a)
    {
        name=n;
        gender=g;
        phone=p;
        age=a;
    }

    void display()
    {
        System.out.println("Name    : "+name);
        System.out.println("Age     : "+age);
        System.out.println("Gender  : "+gender);
        System.out.println("Age     : "+age);
        System.out.println("\n");
    }
}