package committee.nova.mods.skiing.core.data;

import committee.nova.mods.skiing.core.registry.SkiingVillagers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.tags.PoiTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class PoiTypeTagsGenerator extends PoiTypeTagsProvider {
    public PoiTypeTagsGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, future, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(PoiTypeTags.ACQUIRABLE_JOB_SITE).add(SkiingVillagers.SKI_MERCHANT_POI.getKey());
    }
}
