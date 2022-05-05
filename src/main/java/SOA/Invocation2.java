package SOA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Objects;

public class Invocation2 {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:9989/books/allBooks/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            if (httpURLConnection.getResponseCode() != 200){
                System.out.println("Some error!!");
                System.exit(0);

            }

            InputStreamReader in = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output = "";
            while ((output = br.readLine()) != null){
                System.out.println(output);
            }
            httpURLConnection.disconnect();
        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
