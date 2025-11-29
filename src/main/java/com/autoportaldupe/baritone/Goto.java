package com.autoportaldupe.baritone;

import net.minecraft.block.Block;
import net.minecraft.block.TargetBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class Goto {
    public static void goToCoords(double x, double y, double z) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        try {
            String command = "goto " + x + " " + y + " " + z;
            client.player.networkHandler.sendChatMessage("#" + command);
        } catch (Exception e) {
            client.player.sendMessage(Text.literal("§cError: " + e.getMessage()), false);
        }
    }
    public static void goToBlock(String blockname) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        try {
            String command = "goto " +  blockname;
            client.player.networkHandler.sendChatMessage("#" + command);
        } catch (Exception e) {
            client.player.sendMessage(Text.literal("§cError: " + e.getMessage()), false);
        }
    }
}
