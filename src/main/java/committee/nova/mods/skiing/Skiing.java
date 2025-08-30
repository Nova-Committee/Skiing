package committee.nova.mods.skiing;

import com.mojang.logging.LogUtils;
import committee.nova.mods.skiing.core.config.SkiingConfig;
import committee.nova.mods.skiing.core.data.*;
import committee.nova.mods.skiing.core.network.PacketHandler;
import committee.nova.mods.skiing.core.registry.*;
import committee.nova.mods.skiing.core.util.SkiingUtils;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.Arrays;

@Mod(Skiing.MOD_ID)
public class Skiing {

    public static final String MOD_ID = "skiing";


    public static final Logger LOGGER = LogUtils.getLogger();

    public Skiing() {
        LOGGER.debug("Loading up {}!", MOD_ID);
        final var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        final var forgeEventBus = MinecraftForge.EVENT_BUS;

        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::gatherData);

        SkiingBlocks.BLOCKS.register(modEventBus);
        SkiingBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        SkiingTab.TABS.register(modEventBus);
        SkiingEntities.ENTITIES.register(modEventBus);
        SkiingItems.ITEMS.register(modEventBus);
        SkiingPaintingVariants.PAINTINGS.register(modEventBus);
        SkiingVillagers.POINTS_OF_INTEREST.register(modEventBus);
        SkiingVillagers.PROFESSIONS.register(modEventBus);

        forgeEventBus.addListener(this::registerTrades);
        forgeEventBus.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SkiingConfig.COMMON_CONFIG_SPEC,
                MOD_ID + "-common.toml");
        LOGGER.info("{} has finished loading for now!", MOD_ID);
    }

    private void clientSetup(FMLClientSetupEvent event) {
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(SkiingVillagers::registerPointOfInterests);
        PacketHandler.registerMessages();
    }

    private void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var existingFileHelper = event.getExistingFileHelper();
        var future = event.getLookupProvider();

        if (event.includeServer()) {
            generator.addProvider(true, new BlockTagsGenerator(output, future, existingFileHelper));
            generator.addProvider(true, new RecipeGenerator(output));
            generator.addProvider(true, new LootTablesGenerator(output));
        }
        if (event.includeClient()) {
            generator.addProvider(true, new LanguageGenerator(output, MOD_ID));
            generator.addProvider(true, new ItemModelGenerator(output, MOD_ID, existingFileHelper));
        }

    }

    private void registerTrades(VillagerTradesEvent event) {
        if (event.getType().equals(SkiingVillagers.SKI_MERCHANT.get())) {
//            var trades = event.getTrades();
//            trades.get(1).add((trader, rand) -> new MerchantOffer(
//                    new ItemStack(SkiingItems.SNOW_SHOVEL.get(), 1), new ItemStack(Items.EMERALD, 5), 12, 4, 0.02F));
//            trades.get(1).add((trader, rand) -> new MerchantOffer(
//                    SkiingUtils.getRandomPullover(), new ItemStack(Items.EMERALD, 8), 14, 4, 0.02F));
//            trades.get(2).add((trader, rand) -> new MerchantOffer(
//                    SkiingUtils.getRandomVehicle(SkiingItems.SKI_ITEM.get()), new ItemStack(Items.EMERALD, 9), 16, 3, 0.02F));
//            trades.get(2).add((trader, rand) -> new MerchantOffer(
//                    SkiingUtils.getRandomVehicle(SkiingItems.SNOWBOARD_ITEM.get()), new ItemStack(Items.EMERALD, 9), 16, 3, 0.02F));
//            trades.get(2).add((trader, rand) -> new MerchantOffer(
//                    SkiingUtils.getRandomVehicle(SkiingItems.SLED_ITEM.get()), new ItemStack(Items.EMERALD, 11), 12, 3, 0.02F));
//
//            trades.get(3).add((trader, rand) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 7), new ItemStack(SkiingUtils.getRandomVehicle(SkiingItems.SLED_ITEM.get()).getItem(), 2),  12, 2, 0.02F));
//            trades.get(3).add((trader, rand) -> new MerchantOffer(
//                    new ItemStack(SkiingItems.PULLOVER.get(), 1), new ItemStack(Items.EMERALD, 1), 12, 3, 0.02F));
//
//            trades.get(4).add((trader, rand) -> new MerchantOffer(
//                    new ItemStack(SkiingItems.SKI_ITEM.get(), 1), new ItemStack(Items.EMERALD, 1), 14, 3, 0.02F));
//            trades.get(4).add((trader, rand) -> new MerchantOffer(
//                    new ItemStack(SkiingItems.SNOWBOARD_ITEM.get(), 1), new ItemStack(Items.EMERALD, 11), 14, 3, 0.02F));
//
//            trades.get(5).add((trader, rand) -> new MerchantOffer(
//                    new ItemStack(SkiingItems.SLED_ITEM.get(), 1), new ItemStack(Items.EMERALD, 1), 16, 3, 0.02F));
//            trades.get(5).add((trader, rand) -> new MerchantOffer(
//                    new ItemStack(SkiingItems.SNOW_SHOVEL.get(), 1), new ItemStack(Items.EMERALD, 1), 14, 3, 0.02F));
//            trades.get(5).add((trader, rand) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 1), new ItemStack(Items.POWDER_SNOW_BUCKET, 1),18, 5, 0.02F));
            var trades = event.getTrades();

            var novice = new VillagerTrades.ItemListing[]{
                    new VillagerTrades.ItemsForEmeralds(SkiingItems.SNOW_SHOVEL.get(), 5, 1, 12, 4),
                    new VillagerTrades.ItemsForEmeralds(SkiingUtils.getRandomPullover(), 8, 1, 14, 4)
            };
            var apprentice = new VillagerTrades.ItemListing[]{
                    new VillagerTrades.ItemsForEmeralds(SkiingUtils.getRandomVehicle(SkiingItems.SKI_ITEM.get()), 9, 1, 16, 3),
                    new VillagerTrades.ItemsForEmeralds(SkiingUtils.getRandomVehicle(SkiingItems.SNOWBOARD_ITEM.get()), 9, 1, 16, 3),
                    new VillagerTrades.ItemsForEmeralds(SkiingUtils.getRandomVehicle(SkiingItems.SLED_ITEM.get()), 11, 1, 12, 3)
            };
            var journeyman = new VillagerTrades.ItemListing[]{
                    new VillagerTrades.ItemsForEmeralds(SkiingUtils.getRandomSkiStick(), 7, 2, 12, 2),
                    new VillagerTrades.EmeraldForItems(SkiingItems.PULLOVER.get(), 1, 12, 3)
            };
            var expert = new VillagerTrades.ItemListing[]{
                    new VillagerTrades.EmeraldForItems(SkiingItems.SKI_ITEM.get(), 1, 14, 3),
                    new VillagerTrades.EmeraldForItems(SkiingItems.SNOWBOARD_ITEM.get(), 1, 14, 3),
            };
            var master = new VillagerTrades.ItemListing[]{
                    new VillagerTrades.EmeraldForItems(SkiingItems.SLED_ITEM.get(), 1, 16, 3),
                    new VillagerTrades.EmeraldForItems(SkiingItems.SNOW_SHOVEL.get(), 1, 14, 3),
                    new VillagerTrades.ItemsForEmeralds(Items.POWDER_SNOW_BUCKET, 1, 1, 18, 5)
            };

            Arrays.stream(novice).forEach(itemListing -> trades.get(1).add(itemListing));
            Arrays.stream(apprentice).forEach(itemListing -> trades.get(2).add(itemListing));
            Arrays.stream(journeyman).forEach(itemListing -> trades.get(3).add(itemListing));
            Arrays.stream(expert).forEach(itemListing -> trades.get(4).add(itemListing));
            Arrays.stream(master).forEach(itemListing -> trades.get(5).add(itemListing));
        }
    }
}
