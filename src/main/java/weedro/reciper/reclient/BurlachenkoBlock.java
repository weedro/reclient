package weedro.reciper.reclient;

import java.util.UUID;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BurlachenkoBlock extends Block {

    public BurlachenkoBlock() {
        super(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.AMETHYST_BLOCK)
            .strength(99f));
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        entity.sendSystemMessage(Text.of("на пересдачу, чувак"), UUID.randomUUID());
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer,
        ItemStack itemStack) {
        assert placer != null;
        placer.sendSystemMessage(Text.of("i am a javastripper"), UUID.randomUUID());
    }
}
