package com.autoportaldupe;




import com.autoportaldupe.scanner.ChestPositionSave;
import com.mojang.brigadier.arguments.IntegerArgumentType;
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
                    ClientCommandManager.literal("Cpos") // get pos of barrel and redstone block(start pos)
                            .then(argument("KitName", StringArgumentType.string())
                                .executes(context -> {
                                    String kitName = StringArgumentType.getString(context, "KitName");
                                    MinecraftClient client = MinecraftClient.getInstance();
                                    context.getSource().sendFeedback(Text.literal(String.format("Kit: %s is saved", kitName)));
                                    double playerXpos = client.player.getX();
                                    double playerZpos = client.player.getZ();
                                    double playerYpos = client.player.getY();

                                    ChestPositionSave.addChest(kitName, playerXpos, playerZpos, playerYpos );
                                    return 1;
                                 })
                            )
            );

        });


    }
}
