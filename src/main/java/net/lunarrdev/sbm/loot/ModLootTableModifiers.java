package net.lunarrdev.sbm.loot;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.lunarrdev.sbm.item.ModItems;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {
    public static void register() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {

            Identifier id = key.getValue();

            if (id.equals(Identifier.of("minecraft", "chests/stronghold_corridor")) ||
                    id.equals(Identifier.of("minecraft", "chests/stronghold_crossing")) ||
                    id.equals(Identifier.of("minecraft", "chests/stronghold_library"))) {

                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(ModItems.WOLFRAMITE))
                        .conditionally(RandomChanceLootCondition.builder(0.4f));

                tableBuilder.pool(pool);
            }
        });
    }
}
