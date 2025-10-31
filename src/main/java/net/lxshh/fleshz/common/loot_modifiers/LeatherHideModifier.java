package net.lxshh.fleshz.common.loot_modifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.lxshh.fleshz.common.items.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class LeatherHideModifier extends LootModifier {
    public static final Supplier<Codec<LeatherHideModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.create(i ->
                    codecStart(i).apply(i, LeatherHideModifier::new)));

    protected LeatherHideModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext lootContext) {
        for (int i = 0; i < generatedLoot.size(); i++) {
            ItemStack stack = generatedLoot.get(i);

            if (stack.is(Items.LEATHER)) {
                ItemStack hideStack = new ItemStack(ModItems.HIDE.get(), stack.getCount());
                generatedLoot.set(i, hideStack);
            }
        }

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
