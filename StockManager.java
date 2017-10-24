import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.json.*;
import java.util.*;
import java.lang.*;



class Stockaccount  
{
	String search,user,choice1,option,choice;
	
	Scanner sc =new Scanner(System.in);
	JSONParser parser=new JSONParser();
	JSONObject jo=new JSONObject();
	LinkedList list=new LinkedList();
	Stack stack=new Stack();
	LinkedQueue lq=new LinkedQueue();

	Stockaccount(String f1) throws IOException,FileNotFoundException,ParseException
	{
		
		File f2=new File(f1);
		
		jo=(JSONObject)parser.parse(new FileReader(f2));
		JSONArray ja =(JSONArray)jo.get("Stock");

		System.out.println("The available shares are as below :");
		System.out.println();
		for(Object joo:ja)
		{
			JSONObject type1=(JSONObject)(joo);
			String Symbol=(String)type1.get("Symbol");
			long NumberofShare=(long)type1.get("NumberofShare");
			long SharePrice=(long)type1.get("SharePrice");
			
			list.append(Symbol,NumberofShare,SharePrice);
			
			
		}
		System.out.println("Symbol  NumberofShare  SharePrice");
		list.printList();
		System.out.println("");
			
		
		
		do 
		{
			System.out.println("select your Symbol");
			choice=sc.next();
			for(Object joo:ja)
			{
			
				JSONObject type1=(JSONObject)(joo);
				String Symbol=(String)type1.get("Symbol");
				long NumberofShare=(long)type1.get("NumberofShare");
				long SharePrice=(long)type1.get("SharePrice");
			
				
				
	
				if(choice.equals(Symbol))
				{
					System.out.println("The name of the stock is" + " " + Symbol + " " +NumberofShare+ " "+SharePrice);
					System.out.println();
					System.out.println("Enter the option to buy or sell");
					choice1=sc.next();

					if(choice1.equalsIgnoreCase("buy"))
					{
						System.out.println("Before buying the data of shares :");
						System.out.println();
						printReport(Symbol,NumberofShare,SharePrice);
						long temp=buy(Symbol,NumberofShare,SharePrice);
						type1.put("NumberofShare",temp);
					}	

					if(choice1.equalsIgnoreCase("sell"))
					{	
						System.out.println("Before selling the data of shares :");
						System.out.println();
						printReport(Symbol,NumberofShare,SharePrice);
						long temp=sell(Symbol,NumberofShare,SharePrice);
						type1.put("NumberofShare",temp);
				
					}
				
			
				}
		
			}
			System.out.println("If you want to conti. then enter yes otherwise no");
			user=sc.next();
		}while(!user.equalsIgnoreCase("no"));
		System.out.println(jo);
		
		System.out.println("stock data flow ");
		stack.printListStack();
		lq.print();
		
			
	}


	 long buy(String Symbol,long NumberofShare,long SharePrice)
	{
		Scanner sc =new Scanner(System.in);
		Date date = new Date();  
    		String bb=date.toString();	
		
		
		System.out.println("Enter the number of units to buy for" + " " +Symbol);
		int q5=sc.nextInt();	
		NumberofShare=NumberofShare+q5;
		
		System.out.println("After buying the data of shares :");
		System.out.println();
		stack.push(Symbol,NumberofShare,SharePrice);
	
		lq.enqueue(Symbol,bb);
		printReport(Symbol,NumberofShare,SharePrice);
		return NumberofShare;
	}
		

	
	long sell(String Symbol,long NumberofShare,long SharePrice)
	{
		Scanner sc =new Scanner(System.in);
		Date date = new Date();  
    		String bb=date.toString();
		System.out.println();
		System.out.println("Enter the number of units to sell for" + " " +Symbol);
		int q6=sc.nextInt();	
		NumberofShare=NumberofShare-q6;

		stack.push(Symbol,NumberofShare,SharePrice);
		System.out.println("After buying the data of shares------------ :");
		System.out.println();

		lq.enqueue(Symbol,bb);
		printReport(Symbol,NumberofShare,SharePrice);
		return NumberofShare;
	}

	static void printReport(String Symbol,long NumberofShare,long SharePrice)
	{
		System.out.println("stock name " + " " + Symbol);
		System.out.println("stock quantity " + " " + NumberofShare);
		System.out.println("stock price " + " " + SharePrice);
		System.out.println();
		
	}

	 void save()
	{
		try
		{
			FileWriter fw=new FileWriter("sample3.json");
			fw.write(jo.toJSONString());
			System.out.println("File insertion done successfully");
			fw.flush();
			fw.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}


}



class LinkedList
{
	Node head;
	class Node
	{
		String data;
		long NumberofShare;
		long SharePrice;
		Node next;
		Node(String d,long share,long price)
		{
			data=d;
			SharePrice=share;
			NumberofShare=price;
			next=null;
		}
	}
	
	public void append(String new_data,long new_share,long new_price)
	{
		Node new_node=new Node(new_data,new_share,new_price);
		
		if(head==null)
		{
			head=new Node(new_data,new_share,new_price);
			return;
			
		}
		new_node.next=null;
	
		Node last=head;
		
		while(last.next!=null)
		{
		last=last.next;
		}
		
		last.next=new_node;
		return;
	}
	
	public void pop(String data)
	{
		Node temp=head,prev=null;
		
		if(temp!=null && temp.data==data)
		{
			head=temp.next;
			return;
		}
	
		while(temp!=null && temp.data!=data)
		{
			prev=temp;
			temp=temp.next;
		}
		if(temp==null) return;
		prev.next=temp.next;
	} 

	public void printList() 
	{
		Node tnode=head;
		
		
		while(tnode!=null)
		{
		
		System.out.println(tnode.data+"       "+tnode.NumberofShare+"   		    "+tnode.SharePrice);
		
		tnode=tnode.next;
		
		
		}
		
		
	}
	
	
}


class Stack
{
	Node head;
	class Node
	{
		String data;
		long NumberofShare;
		long SharePrice;
		Node next;
		Node(String d,long share,long price)
		{
			data=d;
			SharePrice=share;
			NumberofShare=price;
			next=null;
		}
	}
	
	public void push(String new_data,long new_share,long new_price)
	{
		Node new_node= new Node(new_data,new_share,new_price);
		new_node.next=head;
		head=new_node;	
	}
	
	public void pop()
	{
		Node temp=head,prev=null;
		
		if(temp!=null)
		{
			head=temp.next;
			return;
		}
	
	
		
	} 

	public void printListStack()
	{
		Node t1node=head;
		if(t1node==null)
		{
			System.out.println("stack is empty");
			
		}
		while(t1node!=null)
		{
		
		System.out.println(" "+t1node.data+"  "+t1node.NumberofShare+"  "+t1node.SharePrice);
		
		t1node=t1node.next;
		
		}
		
		
	}
	
	
	
}

class LinkedQueue
{
	Node front,rear;
	int size=0;
	class Node
	{
		String data,date;
		Node next;
		
	}
	public LinkedQueue()
	{
		front=null;
		rear=null;
	}
	
	public boolean isEmpty()
	{
		return (size == 0);
 	}
	
	public void enqueue(String data,String date)
	{
		String str=data+" "+date;
		Node new_node= rear;
		rear=new Node();
		rear.data=str;
		rear.next=null;
		if(isEmpty())
		{
			front=rear;
		}
		else
		{
		new_node.next=rear;	
		}
		size++;
		
	}
	
	public void dequeue()
	{
		String data=front.data;
		front=front.next;
		
		
		
		
		if(isEmpty())
		{
			rear=null;
		}
		size--;
		System.out.println(data);
	
		
	} 
	
	void print()
	{
	while(size!=0)
	{
	dequeue();		
	}
	}

	
}







class StockManager
{
	public static void main(String args[]) throws IOException,FileNotFoundException,ParseException
	{
		Stockaccount s1=new Stockaccount("sample3.json");
		s1.save();
		
	}

}
