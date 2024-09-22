package papka2017.scode.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import papka2017.scode.SCode;
import papka2017.scode.Utils.Hex;

import java.util.List;

public class CodeListener implements Listener {
    private final SCode plugin;

    public CodeListener(SCode plugin) {
        this.plugin = plugin;
    }

    public void triggerListeners(Player player, String code) {
        List<String> listeners = plugin.getConfigManager().getListeners(code);

        for (String listener : listeners) {
            String message = listener;

            message = message.replace("{user}", player.getName());

            if (listener.startsWith("[bc]")) {
                message = message.substring(4);
                Bukkit.broadcastMessage(Hex.color(message));
            } else if (listener.startsWith("[chat]")) {
                message = message.substring(6);
                player.sendMessage(Hex.color(message));
            } else if (listener.startsWith("[subtitle]")) {
                message = message.substring(10);
                player.sendTitle("", Hex.color(message), 10, 70, 20);
            } else if (listener.startsWith("[allsubtitle]")) {
                message = message.substring(14);
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendTitle("", Hex.color(message), 10, 70, 20);
                }
            }
        }
    }
}
