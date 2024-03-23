package dev.exhq.adu.discordApi;

import com.google.gson.Gson;

public interface ApiResponse {
    String getId();
    String getUsername();
    String getAvatar();
    String getDiscriminator();
    int getPublicFlags();
    int getPremiumType();
    int getFlags();
    boolean getBot();
    String getBanner();
    String getAccentColor();
    String getGlobalName();

    default String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
