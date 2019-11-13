package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class ReadFromWeb {
    public static BufferedReader br = null;



    public static void main(String[] args) throws IOException {

        try {
            String apiKey = "ae4397bda9ac27ff8f0b23442abb7cf3"; //fill this in with the API key they email you
            String vancouverweatherquery = "http://api.openweathermap.org/data/2.5/weather?id=6173331&APPID=";
            String theURL = vancouverweatherquery + apiKey;
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            System.out.println(sb);
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }
}
