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
    	super((val>=0)? val: (val * -1));
    	setSign((val < 0)? NEGATIVE: POSITIVE);
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
    	setSign(POSITIVE);
    }
    public String toBase2()
    {
    	String s = super.toBase2();
    	if (!getSign()) return "-" + s;
    	else return s;
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
    		if (super.gt(nj)){
    			SBint result = new SBint (super.minus(nj));
    			result.setSign(NEGATIVE);
    			return result;
    		}
    		Bint njs = new Bint (nj);
    		SBint result = new SBint (njs.minus(this));
    		return result;
    	}
    	else if (this.getSign() && !nj.getSign())
    	{
    		if (super.lt(nj)){
    			Bint njs = new Bint (nj);
    			SBint result = new SBint (njs.minus(this));
    			result.setSign(NEGATIVE);
    			return result;
    		}	
    		SBint result = new SBint (super.minus(nj));
    		return result;
    	}6
    	else if (!this.getSign() && !nj.getSign())
    	{
    		SBint result = new SBint(super.plus(nj));
    		result.setSign(NEGATIVE);
    		return result;
    	}
		SBint result = new SBint (super.plus(nj));
		return result;
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
    public SBint minus (SBint nj)
    {
    	return this.plus(nj.negative());
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
    	int k = n;
    	SBint result = new SBint(this);
    	while (n > 1){
    		result = result.mult(this);
    		n-=1;
    	}
    	if (k%2 == 0)
    		result.setSign(POSITIVE);
    	return result;
    }
    public SBint div (SBint nj)
    {
    	SBint result = new SBint(super.div(nj));
    	if (!this.getSign() || !nj.getSign())
    		result.setSign(NEGATIVE);
    	if (!this.getSign() && !nj.getSign())
    		result.setSign(POSITIVE);
    	if (result.isZero())
    		result.setSign(POSITIVE);
    	return result;
    }
    public String toString()
    {
    	return ((getSign())? super.toString(): "-" + super.toString());
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
         "12. toString",
         "13. toThePower",
         "14. div",
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
             SBint ni = new SBint(i);
             SBint nj = new SBint(ni);
             System.out.println("Constructed SBint: " + ni.toBase2());
             System.out.println("SBint returned by copy constructor: " 
                                 + nj.toBase2());
          }
          else if (choice == 4)
          {
             System.out.print("Enter an int i ");
             int i = Input.nextInt();
             SBint ni = new SBint(i);
             System.out.println("Constructed SBint: " + ni.toBase2());
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
             System.out.print("Enter two integers i  and j  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             SBint ni = new SBint(i);
             SBint nj = new SBint(j);
             SBint nk = ni.plus(nj);
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
             System.out.print("Enter an integer i ");
             int i = Input.nextInt();
             SBint ni = new SBint(i);
             SBint nk = ni.negative ();
             System.out.println(ni.toBase2() + " negative: " +  nk.toBase2());
          }
         
          else if (choice == 10)
          {
             System.out.print("Enter two integers i ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             SBint ni = new SBint(i);
             SBint nj = new SBint(j);
             SBint nk = ni.minus (nj);
             System.out.println(ni.toBase2() + " minus " + nj.toBase2() 
                                + " = " + nk.toBase2());
          }
          else if (choice == 11)
          {
             System.out.print("Enter two integers i ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             SBint ni = new SBint(i);
             SBint nj = new SBint(j);
             SBint nk = ni.mult (nj);
             System.out.println(ni.toBase2() + " mult " + nj.toBase2() 
                                + " = " + nk.toBase2());
          }
          else if (choice == 12)
          {
             System.out.print("Enter an integer i:  ");
             int i = Input.nextInt();
             SBint ni = new SBint(i);
             System.out.println("toString:  " + ni.toString());
          }
          else if (choice == 13)
          {
             System.out.print("Enter an integer i and a power n:  ");
             int i = Input.nextInt();
             int n = Input.nextInt();             
             SBint ni = new SBint(i);
             SBint nk = ni.toThePower (n);
             System.out.println("i ^ n:  " + nk.toBase2());
          }
          else if (choice == 14)
          {
             System.out.print("Enter two integers i ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             SBint ni = new SBint(i);
             SBint nj = new SBint(j);
             SBint nk = ni.div (nj);
             System.out.println(ni.toBase2() + " / " + nj.toBase2() 
                                + " = " + nk.toBase2());
          }
       } while (choice != 0);
    }
}
