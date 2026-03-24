package net.lunarrdev.sbm.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.lunarrdev.sbm.SmallButMighty;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup SBM_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(SmallButMighty.MOD_ID, "sbm_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.TUNGSTEN))
                    .displayName(Text.translatable("itemgroup.sbm.sbm_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.TUNGSTEN);
                        entries.add(ModItems.WOLFRAMITE);
                        entries.add(ModItems.THUNDERFORGED_CLEAVER);
                    }).build());


    public static void registerItemGroups() {
        SmallButMighty.LOGGER.info("Registering Item Groups for " + SmallButMighty.MOD_ID);
    }
}