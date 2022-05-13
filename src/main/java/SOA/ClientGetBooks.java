package SOA;

import ClientServer.Client.ServerConnection;
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

    private static void testSoa(int nbrOfClients) {
        long init = System.currentTimeMillis();
        for (int i = 0; i < nbrOfClients; i++) {
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
                String test = parse(sb.toString());
               // System.out.println(test);


            } catch (MalformedURLException | ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        long elapsedTime = end - init;
        System.out.println("SOA. Nbr of clients: "+nbrOfClients +". Time: " +elapsedTime);
    }

    public static void main(String[] args) {
        testSoa(50);

    }

    public static String parse(String responseBody){
        String output = null;
        JSONArray bookArray = new JSONArray(responseBody);
        for (int i = 0;i < bookArray.length(); i++) {
            JSONObject book = bookArray.getJSONObject(i);
            int storeId = book.getInt("storeId");
            String name = book.getString("name");
            String firstAuthor = book.getString("firstAuthor");
            int publishingYear = book.getInt("publishingYear");
            output = "storeId: "+ storeId + " name: " + name + " firstAuthor: " + firstAuthor + " publishingYear: " +publishingYear;
        }
        return output;
    }
}
