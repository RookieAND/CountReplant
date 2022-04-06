package com.rookieand.countreplant;

import com.rookieand.countreplant.Listener.BlockListener;
import com.rookieand.countreplant.Manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class CountReplant extends JavaPlugin {

    ConsoleCommandSender console = Bukkit.getConsoleSender();
    public static CountReplant plugin;
    public ConfigManager configManager;

    @Override
    public void onEnable() {
        console.sendMessage(ChatColor.GREEN + "[CountReplant] " + ChatColor.WHITE + "플러그인이 성공적으로 "+ ChatColor.YELLOW + "활성화" + ChatColor.WHITE + " 되었습니다!");
        console.sendMessage(ChatColor.GREEN + "[CountReplant] " + ChatColor.WHITE + "Created by "+ ChatColor.YELLOW + "RookieAND_" + ChatColor.AQUA + " (Discord : RookieAND_#1234)");

        getServer().getPluginManager().registerEvents(new BlockListener(), this);

        this.configManager = new ConfigManager(this);
    }

    @Override
    public void onDisable() {
        console.sendMessage(ChatColor.GREEN + "[CountReplant] " + ChatColor.WHITE + "플러그인이 성공적으로 "+ ChatColor.RED + "비활성화" + ChatColor.WHITE + " 되었습니다!");
        console.sendMessage(ChatColor.GREEN + "[CountReplant] " + ChatColor.WHITE + "Created by "+ ChatColor.YELLOW + "RookieAND_" + ChatColor.AQUA + " (Discord : RookieAND_#1234)");
    }

    public void onReload() {
        configManager.reloadConfig();
        console.sendMessage(ChatColor.GREEN + "[CountReplant] " + ChatColor.WHITE + "플러그인이 성공적으로 "+ ChatColor.YELLOW + "리로드" + ChatColor.WHITE + " 되었습니다!");
    }

    public static CountReplant getInstance() { return plugin;}

    public static String getPrefix() { return "§a§lRune | "; }
}
