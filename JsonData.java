import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.*;

public class JsonData
{
    public static void main(String args[])
    {
        JSONParser parser = new JSONParser();
        try
        {
            Object object = parser.parse(new FileReader("sample1.json"));
            JSONObject jsonObject = (JSONObject)object;
           
            JSONArray arr = (JSONArray)jsonObject.get("Rice");
	    System.out.println(arr);

            JSONArray arr1 = (JSONArray)jsonObject.get("Pulse");
	    System.out.println(arr1);
		
	    JSONArray arr2 = (JSONArray)jsonObject.get("Wheat");
	    System.out.println(arr2);
	
	    for(Object j1:arr)
	    {
			JSONObject type1=(JSONObject) (j1);

			String Name=(String)type1.get("Name");
			System.out.println("Name :"+Name);

			long Weight=(long)type1.get("Weight");
			System.out.println("Weight "+Weight);

			long Price=(long)type1.get("Price");
			System.out.println("Price :"+Price);

			System.out.println("The total value is " + " "+ (Weight*Price) );
	    }
	
	   
	    for(Object j1:arr1)
	    {
			JSONObject type1=(JSONObject) (j1);

			String Name=(String)type1.get("Name");
			System.out.println("Name :"+Name);

			long Weight=(long)type1.get("Weight");
			System.out.println("Weight "+Weight);

			long Price=(long)type1.get("Price");
			System.out.println("Price :"+Price);

			System.out.println("The total value is " + " "+ (Weight*Price) );
	    }
	
	    for(Object j1:arr2)
	    {
			JSONObject type1=(JSONObject) (j1);

			String Name=(String)type1.get("Name");
			System.out.println("Name :"+Name);

			long Weight=(long)type1.get("Weight");
			System.out.println("Weight "+Weight);

			long Price=(long)type1.get("Price");
			System.out.println("Price :"+Price);

			System.out.println("The total value is " + " "+ (Weight*Price) );
	    }
	
	
            
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
