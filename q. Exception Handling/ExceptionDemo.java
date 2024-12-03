public class ExceptionDemo {
    public static void main(String[] args) {
        try {
            int X = 10, Y = 0;
            int result = X / Y;
            System.out.println("Result=" + result); //ArithmeticException

        } catch (Exception a) {
            System.out.println("Exception:" + a);
        }
        System.out.println("Last line of the code");
    }
}
