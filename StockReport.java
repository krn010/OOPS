import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.*;

public class StockReport
{
    public static void main(String args[])
    {
	long total=0;
        JSONParser parser = new JSONParser();
        try
        {
            Object object = parser.parse(new FileReader("sample2.json"));
            JSONObject jsonObject = (JSONObject)object;
           
            JSONArray arr = (JSONArray)jsonObject.get("Stock");
	    System.out.println(arr);

	    for(Object j1:arr)
	    {
			JSONObject type1=(JSONObject) (j1);

			String Name=(String)type1.get("Name");
			System.out.println("Name :"+Name);

			long NumberofShare=(long)type1.get("NumberofShare");
			System.out.println("NumberofShare :"+NumberofShare);

			long SharePrice=(long)type1.get("SharePrice");
			System.out.println("SharePrice :"+SharePrice);

			System.out.println("value of stock is " + " "+ (NumberofShare*SharePrice) );
			total=total+(NumberofShare*SharePrice);
	    }
	
	   System.out.println(" The value of total stock is " + " "+ total);
	    
	
            
        }
        catch(FileNotFoundException fe)
        {
            fe.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
