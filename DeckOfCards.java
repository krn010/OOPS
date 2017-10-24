import java.lang.*;
import java.util.*;
class DeckOfCards
{
  public static void main(String[]args)
  {
    int i,j,n,r,l=0;
    String [][]arr=new String[4][9];
    String []suits={"Clubs","Diamonds","Hearts","Spades"};
    String []rank={"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};

     n= suits.length * rank.length;

    String []deck = new String[n];
    for( i=0;i<rank.length;i++){
      for( j=0;j<suits.length;j++){
        deck[suits.length * i +j]=rank[i]+" of "+suits[j];
      }
    }

//shuffle
      for(i=0;i<n;i++){
         r= i+(int)(Math.random()*(n-i));
          String temp = deck[r];
          deck[r]= deck[i];
          deck[i]=temp;
      }
    //  int l=0;
				for(i=0;i<4;i++)
					{
					for(j=0;j<9;j++)
						{
						arr[i][j]=deck[l];
						l++;
						}
					}


      for(i=0;i<4;i++){
	System.out.println();
        System.out.println("Person  "+(i+1)+ "  has the following cards :-------");
        System.out.println();
        for(j=0;j<9;j++){
          //System.out.println(arr[i][j]+"   (Card " + (i+j*4) + ")");
          System.out.println(arr[i][j]);
        }
      }

  }
}
