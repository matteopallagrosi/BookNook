package it.ispw.booknook.logic.boundary;

import it.ispw.booknook.logic.entity.Book;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONManager {

    public static JSONObject getJsonFromUrl(String ISBN) {
        String urlString = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + ISBN + "&key=AIzaSyCzJIp5Ex-gqiy1CwwjovsG-Y6i_-B6asE";
        JSONObject myResponse = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            myResponse = new JSONObject(response.toString());

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return myResponse;
    }

    public static String getImageURL(JSONObject book){
        String thumbnail = null;
        try {
            JSONArray itemsObject = book.getJSONArray("items");
            JSONObject bookInfo = itemsObject.getJSONObject(0);
            JSONObject volumeInfo = bookInfo.getJSONObject("volumeInfo");
            JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
            thumbnail = imageLinks.getString("smallThumbnail");
        }
        catch(JSONException e){
            e.printStackTrace();
        }

        return thumbnail;
    }


}
