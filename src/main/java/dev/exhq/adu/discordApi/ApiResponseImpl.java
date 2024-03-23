package dev.exhq.adu.discordApi;

import com.google.gson.Gson;

public class ApiResponseImpl implements ApiResponse{
    private String id = null;
    private String username = null;
    private String avatar = null;
    private String discriminator = null;
    private int publicFlags = 0;
    private int premiumType = 0;
    private int flags = 0;
    private boolean bot = false;
    private String banner = null;
    private String accentColor = null;
    private String globalName = null;

    @Override
    public String getId() {
        return id;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public String getAvatar() {
        return avatar;
    }
    @Override
    public String getDiscriminator() {
        return discriminator;
    }
    @Override
    public int getPublicFlags() {
        return publicFlags;
    }
    @Override
    public int getPremiumType() {
        return premiumType;
    }
    @Override
    public int getFlags() {
        return flags;
    }
    @Override
    public boolean getBot() {
        return bot;
    }
    @Override
    public String getBanner() {
        return banner;
    }
    @Override
    public String getAccentColor() {
        return accentColor;
    }
    @Override
    public String getGlobalName() {
        return globalName;
    }
    public static ApiResponseImpl fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ApiResponseImpl.class);
    }

    @Override
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
