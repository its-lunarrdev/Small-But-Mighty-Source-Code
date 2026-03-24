package net.lunarrdev.sbm.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.lunarrdev.sbm.item.ModItems;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TUNGSTEN, 1)
                .pattern("CIC")
                .pattern("ISI")
                .pattern("CIC")
                .input('C', ItemTags.COALS)
                .input('I', ModItems.WOLFRAMITE)
                .input('S', Items.NETHERITE_SCRAP)
                .criterion(hasItem(Items.NETHERITE_SCRAP), conditionsFromItem(Items.NETHERITE_SCRAP))
                .offerTo(recipeExporter, Identifier.of("sbm", "tungsten"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.THUNDERFORGED_CLEAVER, 1)
                .pattern("CIC")
                .pattern("CSC")
                .pattern(" S ")
                .input('C', ModItems.TUNGSTEN)
                .input('I', Items.ENDER_EYE)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.TUNGSTEN), conditionsFromItem(ModItems.TUNGSTEN))
                .offerTo(recipeExporter, Identifier.of("sbm", "thunderforged_cleaver"));
    }
}
