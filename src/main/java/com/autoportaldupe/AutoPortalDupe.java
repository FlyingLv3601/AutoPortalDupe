package com.autoportaldupe;

import com.autoportaldupe.scanner.ChestPositionSave;
import com.autoportaldupe.scanner.GetChestPos;
import com.autoportaldupe.baritone.Goto;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;

public class AutoPortalDupe implements ClientModInitializer {

    private boolean searching = false;

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                    ClientCommandManager.literal("Cpos")
                            .then(argument("KitName", StringArgumentType.string())
                                    .executes(context -> {
                                        String kitName = StringArgumentType.getString(context, "KitName");
                                        MinecraftClient client = MinecraftClient.getInstance();
                                        context.getSource().sendFeedback(Text.literal(String.format("Kit: %s is saved", kitName)));
                                        double playerXpos = client.player.getX();
                                        double playerZpos = client.player.getZ();
                                        double playerYpos = client.player.getY();

                                        ChestPositionSave.addChest(kitName, playerXpos, playerZpos, playerYpos);
                                        return 1;
                                    })
                            )
            );

            dispatcher.register(
                    ClientCommandManager.literal("TestChestPos")
                            .then(ClientCommandManager.argument("KitName", StringArgumentType.string())
                                    .executes(context -> {
                                        String kitName = StringArgumentType.getString(context, "KitName");
                                        double[] coordinates = GetChestPos.getKitCoordinates(kitName);


                                        double x = coordinates[0];
                                        double y = coordinates[1];
                                        double z = coordinates[2];
                                        Goto.goToCoords(x, y, z);

                                        return 1;
                                    })
                            )
            );

            dispatcher.register(
                    ClientCommandManager.literal("pos")
                            .then(ClientCommandManager.argument("standPos", StringArgumentType.string())
                                    .executes(context -> {
                                        String kitName = StringArgumentType.getString(context, "KitName");
                                        MinecraftClient client = MinecraftClient.getInstance();
                                        context.getSource().sendFeedback(Text.literal(String.format("Kit: %s is saved", kitName)));
                                        double playerXpos = client.player.getX();
                                        double playerZpos = client.player.getZ();
                                        double playerYpos = client.player.getY();

                                        ChestPositionSave.addPos(kitName, playerXpos, playerZpos, playerYpos);
                                        return 1;
                                    })
                            )
            );

            dispatcher.register(
                    ClientCommandManager.literal("dupeStart")
                            .executes(context -> {
                                return 1;
                            })
            );
        });
    }
}