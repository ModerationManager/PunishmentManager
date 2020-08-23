package me.superbiebel.punishmentmanager.commands;

import me.superbiebel.punishmentmanager.PunishmentManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SystemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        if (args.length == 0| args.length > 1) {
            sender.sendMessage("1 argument is required");
        }else if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage("Check out the wiki if you need help");
        } else if (args[0].equalsIgnoreCase("reloadconfig")) {
            sender.sendMessage("Reloading config");
            PunishmentManager.getPlugin().reloadConfig();
            sender.sendMessage("Config reloaded");
        } else if(args[0].equalsIgnoreCase("sync")) {
            sender.sendMessage("Syncing....");
            PunishmentManager.getAllData(sender.getName());
            sender.sendMessage("Sync complete");
        } else {
            sender.sendMessage("Argument not recognised");
        }
        return true;
    }
}