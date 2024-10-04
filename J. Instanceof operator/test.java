public class test {
    public static void main(String[] args) {
    Animal a= new Animal();
    person p= new person();
    Teacher t= new Teacher();
        System.out.println( p instanceof Animal);// person is a child of Animal;    (true)
        System.out.println( t instanceof person);// teacher is a child of person;   (true)
        System.out.println( t instanceof Animal);// teacher is a child of Animal;   (true)
        System.out.println( p instanceof Teacher);// person is a child of Teacher;  (False)
    }
}