package committee.nova.mods.skiing.core.registry;

import committee.nova.mods.skiing.Skiing;
import committee.nova.mods.skiing.core.util.SkiingTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

/**
 * @Project: skiing
 * @Author: cnlimiter
 * @CreateTime: 2025/5/6 14:53
 * @Description:
 */
public class SkiingTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Skiing.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SKIING_TAB = TABS.register("skiing", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(SkiingItems.SKI_ITEM.get()))
            .title(Component.translatable("itemGroup.skiing"))
            .displayItems((parameters, output) -> {
                for (var item : SkiingItems.ITEMS.getEntries()){
                    output.accept(item.get());
                }
                SkiingPaintingVariants.PAINTINGS.getEntries().forEach((p_269979_) -> {
                    ItemStack itemstack = new ItemStack(Items.PAINTING);
                    CompoundTag compoundtag = itemstack.getOrCreateTagElement("EntityTag");
                    Painting.storeVariant(compoundtag, p_269979_.getHolder().orElseThrow());
                    output.accept(itemstack);
                });
//                Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).getTag(SkiingTags.Blocks.SKIING_MATERIALS).forEach(block -> {
//                    ItemStack itemStack = new ItemStack(this);
//                    CompoundTag compoundTag = new CompoundTag();
//                    compoundTag.putString("skiing_material", Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).toString());
//                    itemStack.addTagElement("EntityTag", compoundTag);
//                    output.accept(itemStack);
//                });
            })
            .build());
}
