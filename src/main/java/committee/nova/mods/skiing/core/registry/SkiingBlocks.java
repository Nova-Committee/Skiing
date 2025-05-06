package committee.nova.mods.skiing.core.registry;

import committee.nova.mods.skiing.Skiing;
import committee.nova.mods.skiing.common.block.SkiRackBlock;
import committee.nova.mods.skiing.core.util.SkiingMaterial;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static committee.nova.mods.skiing.core.registry.SkiingItems.ITEMS;

/**
 * @Project: skiing
 * @Author: cnlimiter
 * @CreateTime: 2025/5/6 14:44
 * @Description:
 */
public class SkiingBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Skiing.MOD_ID);

    static {
        for (Block block : Arrays.stream(SkiingMaterial.values()).map(SkiingMaterial::getBlock).toList()) {
            itemBlock(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath().replace("_planks", "") + "_ski_rack", () -> new SkiRackBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2.5F)));
        }
    }

    private static RegistryObject<Block> baseBlock(String name, Supplier<Block> block) {
        return BLOCKS.register(name, block);
    }

    public static RegistryObject<Block> itemBlock(String name, Supplier<Block> block) {
        return itemBlock(name, block, true);
    }

    public static RegistryObject<Block> itemBlock(String name, Supplier<Block> block, boolean hasItem) {
        return itemBlock(name, block, hasItem, new Item.Properties());
    }

    public static RegistryObject<Block> itemBlock(String name, Supplier<Block> block, Rarity rarity) {
        return itemBlock(name, block, true, new Item.Properties().rarity(rarity));
    }

    public static RegistryObject<Block> itemBlock(String name, Supplier<Block> block, boolean hasItem, Item.Properties properties) {
        var reg = BLOCKS.register(name, block);
        if (hasItem) ITEMS.register(name, () -> new BlockItem(reg.get(), properties));
        return reg;
    }

    public static Block[] getSkiRacks() {
        return BLOCKS.getEntries().stream().filter(block -> block.get() instanceof SkiRackBlock).map(RegistryObject::get).toArray(Block[]::new);
    }
}
