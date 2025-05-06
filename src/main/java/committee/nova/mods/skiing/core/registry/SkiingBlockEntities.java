package committee.nova.mods.skiing.core.registry;

import committee.nova.mods.skiing.Skiing;
import committee.nova.mods.skiing.common.blockentity.SkiRackBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @Project: skiing
 * @Author: cnlimiter
 * @CreateTime: 2025/5/6 14:44
 * @Description:
 */
public class SkiingBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITY_TYPES, Skiing.MOD_ID);

    public static final RegistryObject<BlockEntityType<SkiRackBlockEntity>> SKI_RACK_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("ski_rack", () -> BlockEntityType.Builder.of(SkiRackBlockEntity::new, SkiingBlocks.getSkiRacks()).build(null));
}
