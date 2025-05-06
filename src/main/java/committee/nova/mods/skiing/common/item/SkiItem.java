package committee.nova.mods.skiing.common.item;

import committee.nova.mods.skiing.common.entity.AbstractMultiTextureEntity;
import committee.nova.mods.skiing.core.registry.SkiingEntities;
import net.minecraft.world.entity.EntityType;

public class SkiItem extends AbstractMultiTextureItem {
    public SkiItem(Properties properties) {
        super(properties);
    }

    @Override
    EntityType<? extends AbstractMultiTextureEntity> getEntityType() {
        return SkiingEntities.SKI_ENTITY.get();
    }
}
