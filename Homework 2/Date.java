import java.util.Scanner;

//   For the purposes of this assignment, a date is
//   legal if the month is between 1 and 12, inclusive, the day
//   is between 1 and 31, inclusive, the year is greater than
//   or equal to 1752, and the day of the week is "Sunday", "Monday",
//   "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", or the
//   null string.  Obviously, these conditions don't suffice to make
//   a date legal, since it allows February 31, for example, and doesn't
//   require the day of week to match the date.  We will address these
//   shortcomings in the next assignment   
//
//   To see why 1752 is a special calendar year, type 'cal 1752' at the 
//   Linux command and look at September.
//
//   For homework 2, fill in the code to get the following methods working.
//   Read about defining classes in Chapter 5 of the Savitch book.   This
//   material will be the subject of recitations and lectures this week.
public class Date
{
   private int _month;
   private int _day;
   private int _year;
   private String _dayOfWeek;

   // Check whether m is a legal month, and, if so, set
   //  the month to m
   private void setMonth(int m)
   {
   		if (m >= 1 && m <= 12)
   			{
   				_month = m;
   			}
   }

   // Check whether d is a legal day, and, if so,
   //   set the day to d
   private void setDay(int d)
   {
   		if (d >= 1 && d <= 31)
   			{
   				_day = d;
   			}
   }

   // Check whether y is a legal year, and, if so,
   //   set the year to y
   private void setYear(int y)
   {
   		if (y >= 1752)
   			{
   				_year = y;
   			}
   }

   // Check whether s is a legal day of week, and, if so,
   //    set the day of week to s
   private void setDayOfWeek(String s)
   {
   		if (s == "Sunday" || s == "Monday" || s == "Tuesday" || s == "Wednesday" || s == "Thursday" || s== "Friday" || s== "Saturday")
   		 {
   		 	_dayOfWeek = s;
   		 }
   }


   //  Constructor:  no parameters means set the date to the earliest
   //   legal date.  
   
   //
   //   This method returns a valid date.
   public Date()
   {
      setMonth(9);
      setDay(14);
      setYear(1752);
      setDayOfWeek("Thursday");
   }   

   // return the month of the date
   public int getMonth()
   {
      return _month;
   }

   // return the day of the date.
   public int getDay()
   {
      return _day;
   }

   // return the year of the date
   public int getYear()
   {
      return _year;
   }

   // return the day of week of the date.
   public String getDayOfWeek()
   {
      return _dayOfWeek;
   }

   // constructor.  It should set the day of week to be the given month, day, year,
   //   and the day of week to be the empty string.
   //  If the date is not a valid one, it should set it to the canonical invalid
   //   date, 0/0/0 "".
   public Date(int month, int day, int year)
   {
   		if (month >= 1 && month <= 12)
   			{
   				if (day >= 1 && day <= 31)
   					{
   						if (year >= 1752)
   							{
   								setMonth (month);
   								setDay (day);
   								setYear (year);
   								setDayOfWeek ("");
   							}
   					}
   			}
   		else
   			{
   				_month = 0;
   				_day = 0;
   				_year = 0;
   				_dayOfWeek = "";
   			}
   }

   // constructor.  It should set the day of week to be the given month, day, year,
   //   and the day of week to be the empty string.
   //  If the date is not a valid one, it should set it to the canonical invalid
   //   date, 0/0/0 "".
   public Date(int month, int day, int year, String DayOfWeek)
    {
   		if (month >= 1 && month <= 12)
   			{
   				if (day >= 1 && day <= 31)
   					{
   						if (year >= 1752)
   							{
   								setMonth (month);
   								setDay (day);
   								setYear (year);
   								setDayOfWeek ("");
   							}
   					}
   			}
   		else
   			{
   				_month = 0;
   				_day = 0;
   				_year = 0;
   				_dayOfWeek = "";
   			}
   }

   /* Create a string representation of the date in standard North American
      m/d/yyyy format, followed by day of week, which can be a null string */
   //
   //  The name 'toString' is reserved for creating a string representation
   //  that's suitable for printing.  If an instance x of a class has a
   //  toString() method, then this method will be called whenever
   //  x appears inside a print statement.  For example, 'System.out.println("x = " + x);'
   //  causes x.toString() to create a string representation of x such as
   //  "9/4/2013 Wednesday".  Then, the concatenation "x = " and this string
   //  gives the string "x = 9/4/2013 Wednesday", which is printed by the
   //  print statement.
   public String toString()
   {
      // Here is something to get you started.  Use string concatenation
      //   to get integers into string format ...
      String s = getMonth() + "/" + getDay() + "/" + getYear();
      return s;
   }

   // return true if the year is a leap year; false otherwise ...
   //
   // The rule is the following.  If the date is before 1752, return
   //  false.  Otherwise, if it's divisible by 400, return true.  If
   //  it's not divisible by 400 but divisible by 100, return false.
   //  If it's not divisible by 100 but divisible by 4, return true.
   //  Otherwise, return false.
   public boolean leapYr()
   {
   		if (_year < 1752)
   		{
   			return false;
   		}
   		else if (_year % 400 == 0)
   		{
   			return true;
   		}
   		else if (_year % 100 == 0)
   		{
   			return false;
   		}
   		else if (_year % 4 == 0)
   		{
   			return true;
   		}
      return false;
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
         "1.  Set Month",
         "2.  Set Day",
         "3.  Set Year",
         "4.  Set Day of Week",
         "5.  Date",
         "6.  Get Month",
         "7.  Get Day",
         "8.  Get Year",
         "9.  Get Day of Week",
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
          	 System.out.println("Enter a month");
          	 int Month = Input.nextInt();
             setMonth(Month);
          }
          else if (choice == 2)
          {
             System.out.println("You can insert code for your second test here.");
          }
          else if (choice == 3)
          {
             System.out.println("You can insert code for your second test here.");
          }
          else if (choice == 4)
          {
             System.out.println("You can insert code for your second test here.");
          }
          else if (choice == 5)
          {
             System.out.println("You can insert code for your second test here.");
          }
          else if (choice == 6)
          {
             System.out.println("You can insert code for your second test here.");
          }
          else if (choice == 7)
          {
             System.out.println("You can insert code for your second test here.");
          }
          else if (choice == 8)
          {
             System.out.println("You can insert code for your second test here.");
          }
          else if (choice == 9)
          {
             System.out.println("You can insert code for your second test here.");
          }
      } while (choice != 0);
    }
}
