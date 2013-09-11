import java.util.Scanner;

public class Hw3Menu
{
   /************************************************************/
   /* From here on is infrastructure for the test harness.     */
   /************************************************************/
   private static void printStrings(String[] Strings) 
   {
      for (int i = 0; i < Strings.length; i++) 
        System.out.println(Strings[i]);
   }

   public static void main(String[] args)
   {

      String[] MenuChoices = 
      {
         "0.  Exit",
         "1.  Date(), toString()",
         "2.  Date(m,d,y), toString(), isInLeapYear()",
         "3.  Date(String,Scanner)",
         "4.  daysUntil(date)",
      };
          

      int choice = -1; // Records the option number the user selected
                         //   from the menu

      //  Fill in a do-while loop similar to the one in other test harnesses
      //  we have given you.
   }
}