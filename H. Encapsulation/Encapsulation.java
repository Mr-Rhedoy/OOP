public class Encapsulation {
    private String name;
    private int age;
    public void setName(String name)        /// set and get method;
    {
        this.name= name;
    }
    public String getName()
    {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getAge()
    {
        return age;
    }
    public static void main(String[] args)
    {
        Encapsulation e1= new Encapsulation();
        e1.setName("Rhedoy");
        System.out.println(e1.getName());

        e1.setAge(23);
        System.out.println(e1.getAge());
    }
}
