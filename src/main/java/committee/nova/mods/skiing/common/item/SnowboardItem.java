package committee.nova.mods.skiing.common.item;

import committee.nova.mods.skiing.common.entity.AbstractMultiTextureEntity;
import committee.nova.mods.skiing.core.registry.SkiingEntities;
import net.minecraft.world.entity.EntityType;

public class SnowboardItem extends AbstractMultiTextureItem {
    public SnowboardItem(Properties properties) {
        super(properties);
    }

    @Override
    EntityType<? extends AbstractMultiTextureEntity> getEntityType() {
        return SkiingEntities.SNOWBOARD_ENTITY.get();
    }
}
