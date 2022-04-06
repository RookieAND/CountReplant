package com.rookieand.countreplant.Manager;

import com.rookieand.countreplant.CountReplant;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class CropManager {

    private static final Map<Material, Material> cropToSeed = new HashMap<>();

    static {
        cropToSeed.put(Material.WHEAT, Material.WHEAT_SEEDS);
        cropToSeed.put(Material.POTATOES, Material.POTATO);
        cropToSeed.put(Material.CARROTS, Material.CARROT);
        cropToSeed.put(Material.BEETROOTS, Material.BEETROOT_SEEDS);
        cropToSeed.put(Material.NETHER_WART, Material.NETHER_WART);
    }

    // 작물이 다 자랐는지를 판별하는 메소드
    public static boolean isFullyGrown(Block block) {
        Ageable ageable = (Ageable) block.getBlockData();
        int maximumAge = ageable.getMaximumAge();
        return ageable.getAge() == maximumAge;
    }

    // 파괴한 작물을 다시 심는데 필요한 씨앗의 타입을 알려주는 메소드
    public static Material getSeedFromCrop(Material crop) {
        return cropToSeed.getOrDefault(crop, null);
    }

    // 인벤토리에 씨앗이 있는지를 파악하고, 만약 이것이 존재한다면 씨앗을 제거하는 메소드
    public static void removeCropSeed(Inventory playerInv, Material cropType) {
        int seedItemIndex = -1;
        for (int index = 0; index < playerInv.getSize(); index++) {
            ItemStack currentItem = playerInv.getItem(index);
            if (currentItem != null && currentItem.getType() == cropType) {
                seedItemIndex = index;
                break;
            }
        }
        if (seedItemIndex != -1) {
            ItemStack seedItem = playerInv.getItem(seedItemIndex);
            if (seedItem != null) {
                int seedAmount = seedItem.getAmount();
                seedItem.setAmount(seedAmount - 1);
            }
        }
    }

    public static void replantCrop(Location cropLoc, Material cropType) {
        Bukkit.getScheduler().runTaskLater(CountReplant.getInstance(), () -> {
                cropLoc.getBlock().setType(cropType);
            }, 20L);
    }
}
