package anySchoolProgramming.ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) {
        String site ="https://www.yandex.ru";
        try {
            URL url = new URL(site);
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                int respCode = urlConnection.getResponseCode();
                System.out.println(respCode);
                BufferedReader br =
                        new BufferedReader(
                        new InputStreamReader(
                        urlConnection.getInputStream()));

                String input;
                StringBuilder result = new StringBuilder();
                while((input = br.readLine()) != null){
                    result.append(input).append("\n");
                }

                System.out.println(result);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
