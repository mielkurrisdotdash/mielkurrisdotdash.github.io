import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Meals {
    public static String getContent(String link) throws IOException {
        URL url = new URL("https://www.themealdb.com/api/json/v1/1/search.php?f=m");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();
        System.out.println(status);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();

    }
   public static void main(String args[]) throws IOException {
       System.out.println(getContent("https://www.themealdb.com/api/json/v1/1/search.php?f=m"));
       String jsonString = getContent("https://www.themealdb.com/api/json/v1/1/search.php?f=m"); //assign your JSON String here
       JSONObject obj = new JSONObject(jsonString);

       JSONArray arr = obj.getJSONArray("meals"); // notice that `"posts": [...]`
       for (int i = 0; i < arr.length(); i++)
       {
           String idMeal= arr.getJSONObject(i).getString("idMeal");
           String source= arr.getJSONObject(i).getString("strSource");
           String recipeCategory= arr.getJSONObject(i).getString("strCategory");
           String recipeCuisine= arr.getJSONObject(i).getString("strArea");
           String recipeInstructions= arr.getJSONObject(i).getString("strInstructions");
           ArrayList recipeIngredients = new ArrayList();
           int y=1;
           try{
           while (arr.getJSONObject(i)!=null){
               String ingredient="strIngredient"+y;
               String ingre=arr.getJSONObject(i).getString(ingredient);
               y++;
               if (!ingre.isEmpty() && !ingre.isBlank()){
                   recipeIngredients.add(ingre);
               }
           }}catch(Exception e){}
           System.out.println("<"+source+">"+"<https://schema.org/recipeCategory>"+'"'+recipeCategory+'"'+'.');
           System.out.println("<"+source+">"+"<https://schema.org/recipeCuisine>"+'"'+recipeCuisine+'"'+'.');
           System.out.println("<"+source+">"+"<https://schema.org/recipeInstructions>"+'"'+recipeInstructions+'"'+'.');

           for ( int o = 0; o < recipeIngredients.size(); o++){
               System.out.println("<"+source+">"+"<https://schema.org/recipeIngredient>"+'"'+recipeIngredients.get(o)+'"'+'.');
}

       }
           {

       }
   }
}
