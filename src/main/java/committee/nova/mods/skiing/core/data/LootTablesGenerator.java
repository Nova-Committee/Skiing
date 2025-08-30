package committee.nova.mods.skiing.core.data;

import com.google.common.collect.ImmutableList;
import committee.nova.mods.skiing.core.registry.SkiingBlockEntities;
import committee.nova.mods.skiing.core.registry.SkiingBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.functions.SetContainerContents;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LootTablesGenerator extends LootTableProvider {
    public LootTablesGenerator(PackOutput output) {
        super(output, Collections.emptySet(), VanillaLootTableProvider.create(output).getTables());
    }


    @Override
    public @NotNull List<SubProviderEntry> getTables() {
        return ImmutableList.of(
                new SubProviderEntry(Blocks::new, LootContextParamSets.BLOCK)
//                new SubProviderEntry(ModChestLootTables::new, LootContextParamSets.CHEST),
//                new SubProviderEntry(ModEntityLootTables::new, LootContextParamSets.ENTITY),
//                new SubProviderEntry(ModGiftLootTables::new, LootContextParamSets.GIFT)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, @NotNull ValidationContext validationContext) {
        map.forEach((name, loo) -> {
            loo.validate(validationContext.setParams(loo.getParamSet()).enterElement("{" + name + "}", new LootDataId<>(LootDataType.TABLE, name)));
        });
    }

    public static class Blocks extends BlockLootSubProvider {
        protected Blocks() {
            super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            for (Block block : SkiingBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).toList()) {
                this.add(block, createSkiRackDrops(block));
            }
        }

        private LootTable.Builder createSkiRackDrops(Block block) {
            LootPool.Builder builder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(applyExplosionDecay(block, LootItem.lootTableItem(block)
                            .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                            .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                    .copy("inv", "EntityTag.inv", CopyNbtFunction.MergeStrategy.REPLACE))
                            .apply(SetContainerContents.setContents(SkiingBlockEntities.SKI_RACK_BLOCK_ENTITY.get())
                                    .withEntry(DynamicLoot.dynamicEntry(new ResourceLocation("minecraft", "contents"))))
                    ));

            return LootTable.lootTable().withPool(builder);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return SkiingBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }
}
