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
      Scanner Input = new Scanner(System.in);

      String[] MenuChoices = 
      {
         "0.  Exit",
         "1.  Date()",
         "2.  Date(m, d, y)",
         "3.  Date(prompt, scanner)",
         "4.  daysSinceDec31()",
         "5.  daysSince1751()",
         "6.  days1752ThruLastYr()",
         "7.  daysSince1751()",
      };
          

      int choice = -1; // Records the option number the user selected
                         //   from the menu

      do
      {
          System.out.println("\n ---------------------------------------");
          System.out.println("                MENU");

          printStrings(MenuChoices);

          System.out.print("\n    Which? ");
          choice = Input.nextInt();
          System.out.print("\n\n");
          if (choice == 1)
          {
            Date D = new Date();
             System.out.println(D.toString());
          }
          if (choice == 2)
          {
            Date D = new Date(9, 16, 2013);
             System.out.println(D.toString());
          }
          if (choice == 3)
          {
            Scanner I = new Scanner (System.in);
            Date D = new Date("", I);
             System.out.println(D.toString());
          }
          else if (choice == 4)
          {
             Scanner I = new Scanner (System.in);
             Date D = new Date("", I);
             System.out.println(D.daysSinceDec31());
          }
          if (choice == 5)
          {
            Scanner I = new Scanner (System.in);
            Date D = new Date("", I);
            System.out.println(D.daysSince1751());
          }
          if (choice == 6)
          {
            Scanner I = new Scanner (System.in);
            Date D = new Date("", I);
            System.out.println(D.days1752ThruLastYr());
          }
          if (choice == 7)
          {
            Scanner I = new Scanner (System.in);
            Date D = new Date("", I);
            System.out.println(D.daysUntil(D));
          }
      } while (choice != 0);
    }
}