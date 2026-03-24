package net.lunarrdev.sbm.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.lunarrdev.sbm.SmallButMighty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item TUNGSTEN = registerItem("tungsten", new Item(new Item.Settings()));
    public static final Item WOLFRAMITE = registerItem("wolframite", new Item(new Item.Settings()));

    public static final Item THUNDERFORGED_CLEAVER = registerItem(
            "thunderforged_cleaver",
            new ThunderforgedCleaverItem(
                    ModToolMaterials.TUNGSTEN,
                    new Item.Settings().attributeModifiers(ThunderforgedCleaverItem.createAttributeModifiers(
                            ModToolMaterials.TUNGSTEN, 10, -3.2f
                    )).maxCount(1)
            )
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(SmallButMighty.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SmallButMighty.LOGGER.info("Registering Mod Items for " + SmallButMighty.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(TUNGSTEN);
            entries.add(WOLFRAMITE);
        });
    }
}