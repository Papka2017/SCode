package papka2017.tcode;

import org.bukkit.plugin.java.JavaPlugin;
import papka2017.tcode.Command.CodeCommand;
import papka2017.tcode.Events.CodeListener;
import papka2017.tcode.File.ConfigManager;
import papka2017.tcode.File.DataManager;

public final class TCode extends JavaPlugin {

    private ConfigManager configManager;
    private DataManager dataManager;
    private CodeListener codeListener;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        dataManager = new DataManager(this);
        codeListener = new CodeListener(this);

        getCommand("code").setExecutor(new CodeCommand(this));
        getServer().getPluginManager().registerEvents(codeListener, this);

        getLogger().info("");
        getLogger().info("§x§8§0§2§1§C§9§l==============================");
        getLogger().info("§x§8§0§2§1§C§9§lTCode §f- включен.");
        getLogger().info("§x§8§0§2§1§C§9§l==============================");
        getLogger().info("§fАвтор: §x§8§0§2§1§C§9§lPapka2017");
        getLogger().info("§fВК: §x§8§0§2§1§C§9§l@papka17");
        getLogger().info("§x§8§0§2§1§C§9§l==============================");
        getLogger().info("");
    }

    @Override
    public void onDisable() {
        getLogger().info("");
        getLogger().info("§x§8§0§2§1§C§9§l==============================");
        getLogger().info("§x§8§0§2§1§C§9§lTCode §f- выключен.");
        getLogger().info("§x§8§0§2§1§C§9§l==============================");
        getLogger().info("§fАвтор: §x§8§0§2§1§C§9§lPapka2017");
        getLogger().info("§fВК: §x§8§0§2§1§C§9§l@papka2k17");
        getLogger().info("§x§8§0§2§1§C§9§l==============================");
        getLogger().info("");
    }

    public CodeListener getCodeListener() {
        return codeListener;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
