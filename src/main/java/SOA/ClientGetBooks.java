package SOA;

import org.json.Cookie;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Objects;

public class ClientGetBooks {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:9998/books/list/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            if (httpURLConnection.getResponseCode() != 200){
                System.out.println("Error");
                System.exit(0);
            }
            ObjectInputStream ois = new ObjectInputStream(httpURLConnection.getInputStream());
            //InputStreamReader in = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader br = new BufferedReader(ois);
            String output = "";

            while ((output = br.readLine()) != null){
                JSONObject jsonObject1 = new JSONObject();
                JSONObject jsonObject = Cookie.toJSONObject(output);
                System.out.println("----- Böckerna: -----");

                //System.out.println(output);
            }
            httpURLConnection.disconnect();
        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
