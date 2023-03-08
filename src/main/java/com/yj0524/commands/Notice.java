package com.yj0524.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Notice implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            if (args.length == 0) {
                sender.sendMessage("§c사용법 : /notice <message>");
                return true;
            }
            StringBuilder message = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                message.append(args[i]).append(" ");
            }
            sender.getServer().broadcastMessage("§a[공지] §r" + message);
            return true;
        }
        return false;
    }
}
