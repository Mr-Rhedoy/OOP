import java.util.Scanner;
public class ExceptionproblemSolving {
    public static void main (String[] args){
      while(true)
      {
          try{
              Scanner input = new Scanner(System.in);
              System.out.print("Please Enter Your Num1= ");
              int num1 = input.nextInt();
              System.out.print("Please Enter Num1= ");
              int num2 = input.nextInt();
              int result = num1/num2;
              System.out.println("Result= "+result);

          }catch (Exception a)
          {
              System.out.println("Exception "+a);
              System.out.println("You Entered Wrong Number");
              System.out.println("You Must enter Integer");
          }
      }


    }


}
