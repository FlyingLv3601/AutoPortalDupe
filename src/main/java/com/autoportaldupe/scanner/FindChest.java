package com.autoportaldupe.scanner;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import java.util.HashSet;
import java.util.Set;

public class FindChest {
    public static void printDoubleChests() {
        MinecraftClient c = MinecraftClient.getInstance();
        if (c.player == null || c.world == null) return;

        BlockPos p = c.player.getBlockPos();
        Set<BlockPos> doubles = new HashSet<>(), checked = new HashSet<>();

        for (int x = -64; x <= 64; x++) {
            for (int y = -16; y <= 16; y++) {
                for (int z = -64; z <= 64; z++) {
                    BlockPos pos = p.add(x, y, z);
                    if (checked.contains(pos)) continue;

                    BlockState s = c.world.getBlockState(pos);
                    if (s.isOf(Blocks.CHEST)) {
                        checked.add(pos);
                        BlockPos[] n = {pos.east(), pos.west(), pos.north(), pos.south()};
                        for (BlockPos nb : n) {
                            if (c.world.getBlockState(nb).isOf(Blocks.CHEST)) {
                                doubles.add(pos);
                                checked.add(nb);
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (doubles.isEmpty()) {
            c.player.sendMessage(Text.literal("No double chests found in radius"), false);
        } else {
            for (BlockPos chest : doubles) {
                c.player.sendMessage(Text.literal("Double chest at: " + chest.toShortString()), false);
            }
        }
    }
}