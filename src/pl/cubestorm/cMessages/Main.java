package pl.cubestorm.cMessages;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Timer;

public class Main extends JavaPlugin {
    private static Main instance;
    private Timer timer;

    @Override
    public void onEnable() {
        Main.instance = this;
        Config.create();
        getCommand("cmsg").setExecutor(new Command());

        if (Config.getState())
            this.setTimer();
    }

    @Override
    public void onDisable() {
        if (Config.getState())
            this.timer.cancel();
    }

    public void setTimer() {
        this.timer = new Timer(true);
        this.timer.schedule(new AutoMessage(), 0, Config.getTime() * 1000L);
    }

    public void updateTimer() {
        this.timer.cancel();
        this.setTimer();
    }

    public static Main getInstance() {
        return instance;
    }

    public static String msg(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
