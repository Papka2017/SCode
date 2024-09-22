package papka2017.scode.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import papka2017.scode.File.ConfigManager;
import papka2017.scode.File.DataManager;
import papka2017.scode.SCode;
import papka2017.scode.Utils.Hex;

public class CodeCommand implements CommandExecutor {

    private final SCode plugin;

    public CodeCommand(SCode plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigManager configManager = plugin.getConfigManager();
        DataManager dataManager = plugin.getDataManager();

        if (!(sender instanceof Player)) {
            sender.sendMessage(Hex.color(configManager.getMessage("only_player")));
            return true;
        }

        Player player = (Player) sender;

        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (player.hasPermission("scode.reload")) {
                plugin.reloadConfig();
                configManager.reload();
                dataManager.reload();
                player.sendMessage(Hex.color(configManager.getMessage("reload_success")));
            } else {
                player.sendMessage(Hex.color(configManager.getMessage("no_permission")));
            }
            return true;
        }

        if (args.length < 1) {
            player.sendMessage(Hex.color(configManager.getMessage("usage")));
            return true;
        }

        String code = args[0];

        if (!configManager.isCodeEnabled(code)) {
            player.sendMessage(Hex.color(configManager.getMessage("code_not_found")));
            return true;
        }

        if (dataManager.getPlayerCodeUsage(player.getUniqueId(), code) >= configManager.getMaxUsage(code)) {
            player.sendMessage(Hex.color(configManager.getMessage("code_max_usage")));
            return true;
        }

        if (dataManager.getCodeActivations(code) >= configManager.getActivations(code)) {
            player.sendMessage(Hex.color(configManager.getMessage("code_max_activations")));
            return true;
        }

        configManager.executeCommands(code, player);

        dataManager.incrementPlayerCodeUsage(player.getUniqueId(), code);
        dataManager.incrementCodeActivations(code);

        return true;
    }
}
