package pl.cubestorm.cMessages;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static pl.cubestorm.cMessages.Main.msg;

public class Command implements CommandExecutor {
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(msg("&8----------------------------------"));
            sender.sendMessage(msg("&a/cmsg shop <message> &8- &7Broadcast for shop"));
            sender.sendMessage(msg("&a/cmsg broadcast|bc <message> &8- &7Broadcast for all active players"));
            sender.sendMessage(msg("&a/cmsg reload &8- &7Reload plugin config"));
            sender.sendMessage(msg("&a/cmsg author &8- &7Plugin's author"));
            sender.sendMessage(msg("&8----------------------------------"));
            return true;
        } else {
            switch (args[0]) {
                case "reload":
                    Config.reload();
                    sender.sendMessage(msg("&aConfig reloaded"));
                    return true;

                case "author":
                    sender.sendMessage(msg("&7Discord: &aCubeStorm#8680"));
                    return true;

                case "shop":
                    if (args.length > 1)
                        this.sendToAll(Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR, args, "shop");
                    else
                        sender.sendMessage(msg("&a/cmsg shop <message>"));
                    return true;

                case "broadcast":
                case "bc":
                    if (args.length > 1)
                        this.sendToAll(Sound.ENTITY_ENDER_DRAGON_GROWL, args, "broadcast");
                    else
                        sender.sendMessage(msg("&a/cmsg broadcast <message>"));
                    return true;

            }
        }
        return false;
    }

    private int sendToAll(Sound sound, String[] args, String prefix) {
        args[0] = Config.getPrefix(prefix);
        String message = String.join(" ", args);

        for (Player player : Bukkit.getOnlinePlayers())
            player.playSound(player.getLocation(), sound, 0.7f, 1.0f);

        return Bukkit.broadcastMessage(msg(message));
    }

}
