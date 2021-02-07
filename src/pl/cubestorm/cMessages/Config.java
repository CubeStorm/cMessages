package pl.cubestorm.cMessages;

import java.util.List;

import static pl.cubestorm.cMessages.Main.getInstance;

public class Config {

    public static void reload() {
        getInstance().reloadConfig();
        getInstance().updateTimer();
    }

    public static void create() {
        getInstance().getConfig().options().copyDefaults();
        getInstance().saveDefaultConfig();
    }

    public static boolean getState() {
        return getInstance().getConfig().getBoolean("state");
    }

    public static List<String> getMessageList() {
        return getInstance().getConfig().getStringList("message");
    }

    public static String getPrefix(String prefix) {
        return getInstance().getConfig().getString("prefix." + prefix);
    }

    public static int getTime() {
        return getInstance().getConfig().getInt("time");
    }

    public static boolean getRandom() {
        return getInstance().getConfig().getBoolean("random");
    }

}
