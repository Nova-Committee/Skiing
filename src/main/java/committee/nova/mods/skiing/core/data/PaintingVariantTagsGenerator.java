package committee.nova.mods.skiing.core.data;

import committee.nova.mods.skiing.core.registry.SkiingPaintingVariants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class PaintingVariantTagsGenerator extends PaintingVariantTagsProvider {
    public PaintingVariantTagsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> future, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, future, modId, existingFileHelper);
    }


    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.tag(PaintingVariantTags.PLACEABLE)
                .add(SkiingPaintingVariants.AURORA.getKey(),
                        SkiingPaintingVariants.BAUBLE.getKey(),
                        SkiingPaintingVariants.GONDOLA.getKey(),
                        SkiingPaintingVariants.RUDOLPH.getKey(),
                        SkiingPaintingVariants.SNOWMAN.getKey(),
                        SkiingPaintingVariants.SKIING.getKey(),
                        SkiingPaintingVariants.TREE.getKey()
                );
    }
}
