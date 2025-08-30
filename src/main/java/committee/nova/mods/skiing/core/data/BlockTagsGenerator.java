package committee.nova.mods.skiing.core.data;

import committee.nova.mods.skiing.Skiing;
import committee.nova.mods.skiing.core.util.SkiingTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BlockTagsGenerator extends IntrinsicHolderTagsProvider<Block> {
    public BlockTagsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> future, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.BLOCK, future, block -> block.builtInRegistryHolder().key(), Skiing.MOD_ID, existingFileHelper);
    }

    @Override
    public @NotNull String getName() {
        return "Avaritia Block Tags";
    }
    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.tag(SkiingTags.Blocks.SNOWY_BLOCKS).add(Blocks.ICE, Blocks.BLUE_ICE, Blocks.FROSTED_ICE, Blocks.PACKED_ICE)
                .add(Blocks.POWDER_SNOW, Blocks.SNOW_BLOCK, Blocks.SNOW);
    }
}
