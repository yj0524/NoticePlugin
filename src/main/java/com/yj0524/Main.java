package com.yj0524;

import com.yj0524.commands.Notice;
import com.yj0524.tabcom.NoticeTabCom;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin Enabled");

        getCommand("notice").setExecutor(new Notice());
        getCommand("notice").setTabCompleter(new NoticeTabCom());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin Disabled");
    }
}
