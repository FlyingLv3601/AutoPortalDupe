package com.autoportaldupe.baritone;

import net.minecraft.block.Block;
import net.minecraft.block.TargetBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class Goto {
    public static void goToBlock(String target) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        try {
            String command = "goto " + target;
            client.player.networkHandler.sendChatMessage("#" + command);
        } catch (Exception e) {
            client.player.sendMessage(Text.literal("§cError: " + e.getMessage()), false);
        }
    }
}
