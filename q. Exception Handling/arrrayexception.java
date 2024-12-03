public class arrrayexception {
    public static void main(String[] args) {
        try {
            int[]a= new int[4];
            a[4]=10;            //ArrayIndexOutOfBoundsException

        } catch (Exception a) {
            System.out.println("Exception:" + a);
        }
        System.out.println("Last line of the code");
    }
}
