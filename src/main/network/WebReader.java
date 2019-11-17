package network;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class WebReader {
    public BufferedReader br = null;
    public StringBuilder sb = new StringBuilder();

    public static String parseJason(String json) throws ParseException {
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
        JSONArray jsonArray = (JSONArray) jsonObject.get("weather");
        JSONObject descriptionObject = (JSONObject) jsonArray.get(0);
        return descriptionObject.get("description").toString();
    }


    public String weatherForecast() throws IOException, ParseException {

        try {
            String apiKey = "ae4397bda9ac27ff8f0b23442abb7cf3"; //fill this in with the API key they email you
            String vancouverweatherquery = "http://api.openweathermap.org/data/2.5/weather?id=6173331&APPID=";
            String theURL = vancouverweatherquery + apiKey;
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }
            return "Weather today: " + parseJason(sb.toString());
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }
}
