public class Teacher {
    String name, gender,phone;
    int age;
    //with perameter method
    void setInformation(String a,String b, String c,int d)
    {
        name=a;
        gender=b;
        phone=c;
        age=d;
    }

    //without perameter
    void displayInformation()
    {
        System.out.println("Name   : "+name);
        System.out.println("Gender : "+gender);
        System.out.println("Age    : "+age);
        System.out.println("Phone  : "+phone);
        System.out.println("\n");
    }
}