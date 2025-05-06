package committee.nova.mods.skiing.core.compat;

import committee.nova.mods.skiing.Skiing;
import committee.nova.mods.skiing.core.registry.SkiingItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class SkiingJEIPlugin implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(Skiing.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.useNbtForSubtypes(SkiingItems.PULLOVER.get(), SkiingItems.SKI_ITEM.get(), SkiingItems.SLED_ITEM.get(), SkiingItems.SNOWBOARD_ITEM.get());
        IModPlugin.super.registerItemSubtypes(registration);
    }
}
