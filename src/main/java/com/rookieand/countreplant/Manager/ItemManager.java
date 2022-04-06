package com.rookieand.countreplant.Manager;

import com.rookieand.countreplant.CountReplant;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ItemManager {
    private final ConfigurationSection config;
    //private final List<CountableItem> countableItem;

    public ItemManager(Player player) {
        this.config = CountReplant.plugin.configManager.getConfig();

    }
}
