package dev.exhq.adu.discordApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Communications {
    static String baseEndPointURL = "https://discord.com/api/v8/users/";

    public static ApiResponse GetBaseJson(String token, String id) throws IOException{
        URL url = new URL(baseEndPointURL + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bot " + token);
        int response = connection.getResponseCode();
        BufferedReader reader;
        if (response == 200){
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringResponse = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringResponse.append(line);
            }
            reader.close();

            connection.disconnect();
            System.out.println(stringResponse);
            return ApiResponseImpl.fromJson(stringResponse.toString());
        } else {
            return null;
        }
    }
}
