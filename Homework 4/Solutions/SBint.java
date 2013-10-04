import java.util.Scanner;
public class SBint extends Bint{
	public static final boolean POSITIVE = true;
	public static final boolean NEGATIVE = false;
	private boolean _Sign = true;
	private void setSign(boolean sign)
	{
		_Sign = sign;
	}
    private boolean getSign()
    {
    	return _Sign;
    }
    public SBint()
    {
    	super();
    	setSign(POSITIVE);
    }
    public SBint(int val)
    {
    	super(val);
    	if (val >= 0)
    	{
    	   setSign(POSITIVE);
    	}
    	else
    	{
    		setSign(NEGATIVE);
    	}
    }
    public SBint (SBint ni)
    {
    	super (ni);
    	if (!ni.getSign())
    	{
    		setSign(NEGATIVE);
    	}
    	else setSign(POSITIVE);
    }
    public SBint (Bint ni)
    {
    	super(ni);
    	this.setSign(POSITIVE);
    }
    public String toBase2()
    {
    	String s = super.toBase2();
    	if (getSign() == NEGATIVE)
    		return ("-" + s);
    	return s;
    }
    public void shiftBy (int shift)
    {
    	super.shiftBy(shift);
    	if (super.isZero())
    		setSign(POSITIVE);
    }
    public boolean lt(SBint nj)
    {	
    	if (nj.getSign() && !getSign())
    		return true;
    	if (!nj.getSign() && getSign())
    		return false;
    	if (!nj.getSign() && !getSign())
    	{
    		super.lt(nj);
    	}
    	return true;
    }
    public boolean eq(SBint nj)
    {
    	return !lt(nj) && !nj.lt(this);
    }
    public boolean gt(SBint nj)
    {
    	return !eq (nj) && !lt (nj);
    }
    public boolean ge(SBint nj)
    {
    	return eq (nj) || gt (nj);
    }
    public boolean le(SBint nj)
    {
    	return eq (nj) || lt (nj);
    }
    public SBint plus (SBint nj)
    {
    	if(!this.getSign() && nj.getSign())
    	{
    		nj.minus(this);
    	}
    	else if (this.getSign() && !nj.getSign())
    	{
    		this.minus(nj);
    	}
    	else if (!this.getSign() && !nj.getSign())
    	{
    		this.plus(nj);
    		setSign(NEGATIVE);
    	}
    	return this.plus(nj);
    }
    public SBint plus1 ()
    {
    	return this.plus(new SBint(1));
    }
    public SBint negative()
    {
    	int i = this.toInt();
    	if (getSign() == false)
    		return new SBint(i);
    	return new SBint (-i);
    }
    public Bint minus (Bint nj)
    {
    	// return this.plus(nj.negative());
    	return new SBint(0);
    }
    public SBint mult (SBint ni)
    {
    	SBint s = new SBint (super.mult(ni));
    	if (!this.getSign() || !ni.getSign())
    	{
    		s.setSign(NEGATIVE);
    	}
    	return s;
    }
    public SBint toThePower (int n)
    {
    	return new SBint();
    }
    public SBint div (SBint nj)
    {
    	return new SBint(0);
    }
    public String toString()
    {
    	return new String ("");
    }
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
         "1.  setSign, getsign",
         "2.  Constructor (value)",
         "3.  Copy constructor",
         "4.  tobase2",
         "7.  shiftBy",
         "5.  lt(), gt(), eq(), le(), ge()",
         "6.  plus",
         "8.  plus1",
         "9.  negative",
         "10. minus",
         "11. mult",
         "13. toThePower",
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
             System.out.print("Enter a boolean: NEGATIVE = false  POSITIVE = true ");
             boolean i = Input.nextBoolean();
             SBint ni = new SBint();
             ni.setSign(i);
             System.out.println("\n A call to getsign returns " +  
                      ni.getSign());
          }
          if (choice == 2)
          {
             System.out.print("Enter an integer i:  ");
             int i = Input.nextInt();
             SBint ni = new SBint(i);
             System.out.println("Constructed Bint: " + ni.toBase2());
          }
          if (choice == 3)
          {
             System.out.print("Enter a non-negative integer i:  ");
             int i = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nj = new Bint(ni);
             System.out.println("Constructed Bint: " + ni.toBase2());
             System.out.println("Bint returned by copy constructor: " 
                                 + nj.toBase2());
          }
          else if (choice == 4)
          {
             System.out.print("Enter a non-negative integer i and a shift j  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             System.out.println("Constructed Bint: " + ni.toBase2());
             ni.shiftBy(j);
             System.out.println("After shift by j: " + ni.toBase2());
          }
          else if (choice == 5)
          {
             System.out.print("Enter two non-negative integers i  and j  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nj = new Bint(j);
             System.out.println ("i < j: " + ni.lt(nj));
             System.out.println ("i <= j: " + ni.le(nj));
             System.out.println ("i == j: " + ni.eq(nj)); 
             System.out.println ("i >= j: " + ni.ge(nj));
             System.out.println ("i > j: " + ni.gt(nj));
          }
          else if (choice == 6)
          {
             System.out.print("Enter two non-negative integers i  and j  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nj = new Bint(j);
             Bint nk = ni.plus(nj);
             System.out.println(ni.toBase2() + " + " + nj.toBase2() + " = " 
                                + nk.toBase2());
          }
          else if (choice == 7)
          {
             System.out.print("Enter two non-negative integers i  and j  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nj = new Bint(j);
             Bint nk = ni.mult (nj);
             System.out.println(ni.toBase2() + " * " + nj.toBase2() + 
                                " = " + nk.toBase2());
          }
          else if (choice == 8)
          {
             System.out.print("Enter two non-negative integers i  and j  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nk = ni.toThePower(j);
             System.out.println(ni.toBase2() + "^" + j + " = " + nk.toBase2());
          }
          else if (choice == 9)
          {
             System.out.print("Enter two non-negative integers i  and j  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nj = new Bint(j);
             Bint nk = ni.minus (nj);
             System.out.println(ni.toBase2() + " - " + nj.toBase2() 
                                + " = " + nk.toBase2());
          }
         
          else if (choice == 10)
          {
             System.out.print("Enter two non-negative integers i  and j w/ j > 0  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nj = new Bint(j);
             Bint nk = ni.mod (nj);
             System.out.println(ni.toBase2() + " mod " + nj.toBase2() 
                                + " = " + nk.toBase2());
          }
          else if (choice == 11)
          {
             System.out.print("Enter two non-negative integers i, j with j > 0  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nj = new Bint(j);
             Bint nk = ni.div (nj);
             System.out.println(ni.toBase2() + " div " + nj.toBase2() 
                                + " = " + nk.toBase2());
          }
          else if (choice == 13)
          {
             System.out.print("Enter a non-negative integer i:  ");
             int i = Input.nextInt();
             Bint ni = new Bint(i);
             System.out.println("Binary representation:  " + ni.toBase2());
             System.out.println("Conversion back to int: " + ni.toInt());
          }
       } while (choice != 0);
    }
}