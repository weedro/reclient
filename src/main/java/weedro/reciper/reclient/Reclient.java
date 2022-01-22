package weedro.reciper.reclient;

import static net.minecraft.server.command.CommandManager.literal;

import java.util.UUID;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.network.MessageType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Reclient implements ModInitializer {

    private static final String NAMESPACE = "reclient";
    public static final Block BURLACHENKO = new BurlachenkoBlock();

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "burlach"), BURLACHENKO);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "burlach"),
            new BlockItem(BURLACHENKO, new Item.Settings().group(
                ItemGroup.BUILDING_BLOCKS)));

        CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> dispatcher.register(
            literal("generaterecipe").executes(context -> {
                ServerPlayerEntity player = context.getSource().getPlayer();
                player.sendMessage(Text.of("Why do you call me??"),
                    MessageType.CHAT,
                    UUID.randomUUID());

                String msg = "";
                try {
                    msg = RecipeGenerator.generate(player.getName().asString());
                } catch (Exception e) {
                    msg = e.getMessage();
                }

                player.sendMessage(Text.of(msg), MessageType.CHAT, UUID.randomUUID());
                return 0;
            }))));
    }

}
