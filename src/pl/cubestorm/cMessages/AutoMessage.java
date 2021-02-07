package pl.cubestorm.cMessages;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.TimerTask;

import static pl.cubestorm.cMessages.Main.msg;

class AutoMessage extends TimerTask {
    public static ArrayList<String> messageList;
    private int index;

    /**
     * Constructor
     */
    public AutoMessage() {
        AutoMessage.messageList = (ArrayList<String>) Config.getMessageList();
        this.index = 0;
    }

    /**
     * Broadcast message every given seconds
     */
    @Override
    public void run() {
        if(Bukkit.getOnlinePlayers().size() > 0) {
            int i;

            if (Config.getRandom()) {
                i = randomInteger(AutoMessage.messageList.size());
            } else {
                i = this.index;
                ++this.index;
                
                if(this.index > AutoMessage.messageList.size() - 1)
                    this.index = 0;
            }

            Bukkit.broadcastMessage(msg(Config.getPrefix("main") + AutoMessage.messageList.get(i)));

            for (Player player : Bukkit.getOnlinePlayers())
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_HIT, 1.0f, 1.0f);
        }
    }

    /**
     * Generate random number with specific range
     * @param number - range
     */
    private int randomInteger(int number) {
        return (int) (Math.random() * number);
    }
}
