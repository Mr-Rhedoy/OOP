public class Teacher extends person {
    String qualificaton;

    @Override
    void displayinfo()
    {
        System.out.println("Name= "+name);
        System.out.println("Age= "+age);
        System.out.println("Qualification= "+qualificaton);

    }
}