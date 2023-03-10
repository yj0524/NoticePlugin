package com.yj0524;

import com.yj0524.tabcom.NoticeTabCom;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {

    public String noticeMessage;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin Enabled");

        getCommand("notice").setTabCompleter(new NoticeTabCom());

        // Config.yml 파일 생성
        loadConfig();
        File cfile = new File(getDataFolder(), "config.yml");
        if (cfile.length() == 0) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin Disabled");
    }

    private void loadConfig() {
        // Load chest size from config
        FileConfiguration config = getConfig();
        noticeMessage = config.getString("noticeMessage", "&a[공지] &r");
        // Save config
        config.set("noticeMessage", noticeMessage);
        saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("notice")) {
            if (sender.isOp()) {
                if (args.length == 0) {
                    sender.sendMessage("§c사용법 : /notice <message>");
                    return true;
                }
                StringBuilder message = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    message.append(args[i]).append(" ");
                }
                sender.getServer().broadcastMessage(noticeMessage.replace("&", "§") + message);
                return true;
            }
            sender.sendMessage("§c당신은 이 명령어를 사용할 권한이 없습니다.");
            return true;
        }
        return false;
    }
}
