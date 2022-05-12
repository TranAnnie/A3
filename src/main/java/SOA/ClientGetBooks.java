package SOA;

import ClientServer.Shared.Book;
import org.json.Cookie;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class ClientGetBooks {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:9998/books/list/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            if (httpURLConnection.getResponseCode() == 200 || httpURLConnection.getResponseCode() == 201){
                //ObjectInputStream ois = new ObjectInputStream(httpURLConnection.getInputStream());
                InputStreamReader in = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader br = new BufferedReader(in);
                String output = "";
                ArrayList<Book> arrayList = new ArrayList<>();
                while (br.readLine() != null){
                    

                    System.out.println("----- Böckerna: -----");
                    for (Book a:arrayList) {
                        System.out.println(a);
                    }

                }
                httpURLConnection.disconnect();
            } else{
                System.out.println("Error");
                System.exit(0);
            }

        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
