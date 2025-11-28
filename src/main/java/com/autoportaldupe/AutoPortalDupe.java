package com.autoportaldupe;




import com.autoportaldupe.scanner.FindChest;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;


public class AutoPortalDupe implements ClientModInitializer {

    private boolean searching = false;

    @Override
    public void onInitializeClient() {

        // HUD текст
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {

            dispatcher.register(
                    ClientCommandManager.literal("search")
                            .executes(context -> {
                                searching = !searching;
                                context.getSource().sendFeedback(
                                        Text.literal("Поиск: " + (searching ? "Включен" : "Выключен"))
                                );
                                return 1;
                            })
            );

            dispatcher.register(
                    ClientCommandManager.literal("scanWorld")
                            .executes(context -> {
                                FindChest.printDoubleChests();
                                return 1;
                            })
            );

        });


    }
}
