package committee.nova.mods.skiing.core.util;

import committee.nova.mods.skiing.Skiing;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class SkiingTags {
    public static class Blocks {
        public static final TagKey<Block> SNOWY_BLOCKS = modTag("snowy_blocks");

        private static TagKey<Block> modTag(String name) {
            return TagKey.create(Registries.BLOCK, new ResourceLocation(Skiing.MOD_ID, name));
        }
    }
}
