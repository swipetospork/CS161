import java.util.Scanner;

public class Date
{
   private int _month;
   private int _day;
   private int _year;
   private static int[] MonthLengths
              = {0,31,28,31,30,31,30,31,31,30,31,30,31};
   private static String [] dayOfTheWeek
              = {"", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
   private static int [] MonthSums
              = {0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
   private static int [] MonthSumsLeapYear
              = {0, 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
   // set month to given integer with no error checking
   private void setMonth(int m)
   {
      _month = m;
   }

   // set day to given integer with no error checking
   private void setDay(int d)
   {
      _day = d;
   }

   // set year to given integer with no error checking
   private void setYear(int y)
   {
      _year = y;
   }

   //  Constructor:  no parameters means set the date to the earliest
   //   legal date, 9/14/1752
   public Date()
   {
      setMonth(9);
      setDay(14);
      setYear(1752);
   }   

   // return the month
   public int getMonth()
   {
      return _month;
   }

   // return the day
   public int getDay()
   {
      return _day;
   }

   // return the year
   public int getYear()
   {
      return _year;
   }


   /*  The following allows a date variable to determine whether somebody has 
       just given it an illegitimate date, so that it can either insist
       on a new one or toss it out and take on a default date that's
       known to be legitimate.

    Important to remember:  The date class considers a date legitimate if it
       occurs on the calendar and is later than 09/13/1752.  The
       reason for starting 09/14/1752 is funny things that happened to
       the calendar in the 18th century that would make the assignment 
       more difficult.  At the Linux prompt, type cal 1752 and check
       out September.  */
   private boolean isLegal()
   {
    if (getYear() <= 1752 && getMonth() <= 13 && getDay() <= 13)
    {
      return false;
    }
    if (getYear() >= 1752)
      {
        if (getMonth() > 0 && getMonth() <= 12)
          {
            if (getDay() > 0 && getDay() <= MonthLengths [getMonth() +1])
            {
              return true;
            }
          }
      }
      return false;
    }

   // If the given date is a legal one, set the month, day, year to m, d, y;
   // Otherwise, set them all to zero
   public Date(int m, int d, int y)
   {
      setMonth (m);
      setDay (d);
      setYear (y);
      if (isLegal () == false)
      {
          setMonth (0);
          setDay (0);
          setYear (0);
      }
   }

   // Prompt for a month, day, year, separated by spaces.  If the
   // date is a legal one, set the month, day, year to m, d, y;
   // Otherwise, set them all to zero
   public Date(String prompt, Scanner I)
   {
       System.out.print(prompt 
                        + "\n(month, day, yr separated by spaces): ");
       setMonth(I.nextInt());
       setDay(I.nextInt());
       setYear(I.nextInt());
       if (isLegal () == false)
       {
          setMonth (0);
          setDay (0);
          setYear (0);
       }
   }

   /* Create a string representation of the date in standard North American
      m/d/yyyy Dayofweek format. e.g. "9/10/2013 Tuesday"  */
   public String toString()
   {
       String s = getMonth() + "/" + getDay() + "/" + getYear() + " " + dayOfWeek();
      return s;
   }

   // Determine whether the year is a leap year.  If the date is not a legal
   //  one return false.
   public boolean isInLeapYear()
   {
      if (getYear() < 1752) return false;
      else if (getYear() % 400 == 0) return true;
      else if (getYear() % 100 == 0) return false;
      else if (getYear() % 4 == 0) return true;
      else return false;
   }

   // Return the number of days in the date's year from January 1 through
   //   the date, inclusive.   If the date is not legal, return 0.
   //   Eventually, you will want to make it private.  Leave it public
   //   so that we can test it from another class.
   public int daysSinceDec31()
   {
      int numberOfDays = 0;
      boolean leapYear = isInLeapYear();
      if (isLegal () == false)
      {
        return 0;
      }
      if (leapYear == true)
      {
        numberOfDays += (MonthSumsLeapYear[getMonth()] + getDay());
        return numberOfDays;
      }
      numberOfDays += (MonthSums[getMonth()] + getDay());
      return numberOfDays;
   }

   // Return the number of days that would lie between 1/1/1752 through 
   // December 31 of the year that precedes the year of the date if
   // all of 1752 were part of the Gregorian calendar.  Return 0 if
   // the date is not a legal one.
   public int days1752ThruLastYr()
   {
       int numberOfYears = (getYear() -1) - 1751;
       int numberOfDays = 0;
       if (isLegal() == false)
       {
        return 0;
       }
       numberOfDays = numberOfYears * 365;
       numberOfDays += (numberOfYears + 3) / 4;
       numberOfDays -= (numberOfYears + 51) / 100;
       numberOfDays += (numberOfYears + 151) / 400;
       return numberOfDays;
   }

   // Return the number of days that would occur from 1/1/1752 through the 
   //  date if all of 1752 were part of the Gregorian calendar.  Return 0 if
   //  the date is not a legal one.  1752 would be a leap year.  Eventually
   //  this should be private; keep it public so we can grade it.
   public int daysSince1751()
   { 
      int j = 0;
       int numberOfDays = 0;
       boolean legal = isLegal();
       if (legal == false)
       {
        return 0;
       }
       numberOfDays += days1752ThruLastYr() + daysSinceDec31();
       return numberOfDays;
   }

   // Return the number of days from date1 through date2.  If date1
   //   is after date2, the method returns a negative number.   If one
   //   of the dates is illegal, it returns 0.
   public int daysUntil(Date date2)
   {
      return 0;
   }

   // Return the day of the week that the date falls on.
   // If the date is not legal, it returns the null string ""
   public String dayOfWeek()
   {
      if (isLegal() == false)
      {
        return "yall done fucked up";
      } 
      int day = (daysSince1751 () +4)  % 7;
      return dayOfTheWeek [day];
   }

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
         "4.  daysSince1751()",
         "2.  daysSinceDec31()",
         "3.  days1752ThruLastYr()",
         "4.  daysSince1751()",
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
             Date D = new Date(9, 16, 2013);
             System.out.println(D.daysSinceDec31());
          }
      } while (choice != 0);
    }
}