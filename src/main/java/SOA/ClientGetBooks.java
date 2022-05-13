package SOA;

import ClientServer.Shared.Book;
import org.json.Cookie;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ClientGetBooks {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:9998/books/list/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept", "application/json");

            if(httpURLConnection.getResponseCode()!= 200){

                System.err.println("Some error happend");
                System.exit(0);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String output = "";
            while((output = br.readLine()) !=null){
                sb.append(output);

            }
            br.close();
            httpURLConnection.disconnect();
            parse(sb.toString());
            

        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void parse(String responseBody){
        JSONArray bookArray = new JSONArray(responseBody);
        for (int i = 0;i < bookArray.length(); i++) {
            JSONObject book = bookArray.getJSONObject(i);
            int storeId = book.getInt("storeId");
            String name = book.getString("name");
            String firstAuthor = book.getString("firstAuthor");
            int publishingYear = book.getInt("publishingYear");
            System.out.println("storeId: "+ storeId + " name: " + name + " firstAuthor: " + firstAuthor + " publishingYear: " +publishingYear);
        }

    }
}
