package dev.exhq.adu;

import dev.exhq.adu.discordApi.Communications;
import io.javalin.Javalin;

import static dev.exhq.adu.discordApi.Communications.GetBaseJson;


public class Main {
    static String botToken = System.getenv("aduToken");

    static String getAnimatedAvatarURL(String userId, String avatarHash) {
        return "https://cdn.discordapp.com/avatars/" + userId + "/" + avatarHash + ".gif";
    }

    static String getStaticAvatarURL(String userId, String avatarHash) {
        return "https://cdn.discordapp.com/avatars/" + userId + "/" + avatarHash + ".png";
    }

    public static void main(String[] args) {

        var app = Javalin.create(javalinConfig -> {
                    javalinConfig.bundledPlugins.enableCors(cors -> {
                        cors.addRule(it -> {
                            it.anyHost();
                        });
                    });
                }).get("/", ctx -> {
                    ctx.result("hi");
                })
                .get("/v1/{id}.json", ctx -> {
                    var id = ctx.pathParam("id");
                    if (id.contains("@")){
                        ctx.status(418);
                        ctx.result("im a shiggy");
                        return;
                    }
                    var info = Communications.GetBaseJson(botToken, id);
                    if (info == null) {
                        ctx.status(400);
                        ctx.result("bro wtf");
                        return;
                    } else {
                        ctx.json(info.toJson());
                    }

                })
                .get("/v1/{id}/avatar*", ctx -> {
                    var id = ctx.pathParam("id");
                    if (id.contains("@")){
                        ctx.status(418);
                        ctx.result("im a shiggy");
                        return;
                    }
                    var userData = Communications.GetBaseJson(botToken, id);
                    if (userData == null) {
                        ctx.status(400);
                        ctx.result("bro wtf");
                        return;
                    }
                    if (userData.getAvatar().startsWith("a_")) {
                        ctx.redirect(getAnimatedAvatarURL(userData.getId(), userData.getAvatar()));
                    } else {
                        ctx.redirect(getStaticAvatarURL(userData.getId(), userData.getAvatar()));
                    }
                })
                .start(5050);
    }
}