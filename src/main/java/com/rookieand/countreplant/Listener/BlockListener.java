package com.rookieand.countreplant.Listener;

import com.rookieand.countreplant.Manager.CropManager;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.List;


public class BlockListener implements Listener {

    private static final List<Material> cropList = Arrays.asList(Material.WHEAT, Material.POTATOES, Material.CARROTS, Material.BEETROOTS, Material.NETHER_WART);

    @EventHandler
    public void cropBreakEvent(BlockBreakEvent e) {
        Player player = e.getPlayer();
        // 해당 유저가 서바이벌 상태인지를 먼저 판별해야 함.
        if (player.getGameMode() == GameMode.SURVIVAL) {
            // 현재 유저가 파괴한 블럭이 작물 관련 블럭이며, 작물이 다 자란 상태인지를 판별
            Block cropBlock = e.getBlock();
            Material cropType = cropBlock.getType();
            if (cropList.contains(cropType) && CropManager.isFullyGrown(cropBlock)) {
                // 현재 유저가 자동심기에 필요한 씨앗을 가지고 있는지를 판별하고, 맞다면 자동심기를 진행시킴
                Inventory playerInv = player.getInventory();
                if (playerInv.contains(CropManager.getSeedFromCrop(cropType))) {
                    CropManager.removeCropSeed(playerInv, cropBlock.getType());
                    CropManager.replantCrop(cropBlock.getLocation(), cropType);
                }
            }
        }
    }
}
