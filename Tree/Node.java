import java.util.*;
import java.io.File;
import java.io.IOException;
public class Node {
	String _question;
	Node _yesNode;
	Node _noNode;
	 Node(String q)
	 {
	 	_question = q;
	 	_yesNode = null;
	 	_noNode = null;
	 }
	 Node(String q, Node noNode, Node yesNode)
	 {
		_question = q;
	 	_yesNode = yesNode;
	 	_noNode = noNode;
	 }
	 public Node getNoNode()
	 {
	 	return _noNode;
	 }
	 public Node getYesNode()
	 {
	 	return _yesNode;
	 }
	 public String getQuestion()
	 {
	 	return _question;
	 }
	 public static Node insertNode(Node OldAnimalNode, String Question, String YesNo, String NewAnimal)
	 {
	 	Node newNode = new Node (NewAnimal);
	 	if (YesNo.equals("y"))
	 	{
	 		Node parent = new Node(Question, OldAnimalNode, newNode);
	 		return parent;
	 	}
	 	else{
			Node parent = new Node(Question, newNode, OldAnimalNode);
			return parent;
	 	}
	 }
	 public static Node traverse(Node Root, Scanner Input)
	 {
	 	if (Root.getYesNode() == null && Root.getNoNode() == null)
	 	{
	 		System.out.println ("Are you thinking of a " + Root.getQuestion() + "?");
	 		if (Input.nextLine().equals("y"))
	 		{
	 			System.out.println ("MUAHAHA I HAVE WON!!! AND NOW I SHALL RETURN...... wait for it....... NULL!!!!!!");
	 			return null;
	 		}
	 		else 
	 		{
	 			System.out.println ("What animal were you thinking of?");
	 			String animal = Input.nextLine();
	 			System.out.println ("Give a question that would distinguish a " + Root.getQuestion() + " from a " + animal);
	 			String question = Input.nextLine();
	 			System.out.println ("Is the answer to that question ’yes’ for " + animal + "?");
	 			String answer = Input.nextLine();
	 			return insertNode(Root, question, answer, animal);

	 		}
	 	}
	 	else
	 	{
	 		System.out.println(Root.getQuestion());
	 		String answer = Input.nextLine();
	 		if (answer.equals("y"))
	 			{
	 				Root._yesNode = traverse (Root.getYesNode(), Input);
	 			}
	 		else Root._noNode = traverse (Root.getNoNode(), Input);
	 	}
	 	return null;
	 }
	 public static Node readTree(String fName)
		{
			Scanner Input = null;
			try
				{
					Input = new Scanner(new File(fName));
				}
			catch (IOException E)
				{
					System.out.println("readIn: IOException!!");
					System.exit(1);
				}
			return readTree(Input);
		}
		public static Node readTree(Scanner Input)
		{
			int type = Input.nextInt();
			String q = Input.nextLine();
			if (type == 0)
				return new Node(q);
			else
				return new Node(q, readTree(Input), readTree(Input));
		}
		static void printSpaces(int n)
		{
			for (int i = 0; i < n; i++)
			System.out.print(" ");
		}
		static void printSubtree(Node N, int level)
		{
			printSpaces(level*4); // pad out to the indentation level
			System.out.println(N.getQuestion()); // print the node in the root
			if (N.getYesNode() != null)
				// recursively print subtree rooted at ’yes’ child:
				printSubtree(N.getYesNode(), level+1);
			if (N.getNoNode() != null)
				// recursively print subtree rooted at ’no child:
				printSubtree(N.getNoNode(), level+1); // print second subtree
		}
	 public static void main (String [] args)
	 {
	 	System.out.println ("Name an animal: ");
	 	Scanner Input = new Scanner(System.in);
	 	String animal = Input.nextLine();
	 	Node _node = new Node (animal);
	 	//Node Root = traverse (_node, Input);
	 	Node Root = null;
	 	String cont = "y";
	 	while (!cont.equals("n"))
	 	{
	 		if (Root == null)
	 		{
	 			Root = traverse(_node, Input);
	 		}
	 		else traverse(Root, Input);
	 		System.out.println("Try Again?");
	 		cont = Input.nextLine();
	 	} 

	 }
}