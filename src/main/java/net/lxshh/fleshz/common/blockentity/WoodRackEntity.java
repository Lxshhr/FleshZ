package net.lxshh.fleshz.common.blockentity;

import net.lxshh.fleshz.common.recipes.ModRecipes;
import net.lxshh.fleshz.common.recipes.RackRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.Optional;

public class WoodRackEntity extends BlockEntity {
    public int dryingTime;
    private int processTime;

    private final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public void setSize(int size) {
            super.setSize(1);
        }
    };

    public WoodRackEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.WOOD_RACK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        tag.putInt("drying_time", dryingTime);
        tag.putInt("process_time", processTime);
        tag.put("inventory", itemHandler.serializeNBT(provider));
        super.saveAdditional(tag, provider);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        this.dryingTime = tag.getInt("drying_time");
        this.processTime = tag.getInt("process_time");
        itemHandler.deserializeNBT(provider, tag.getCompound("inventory"));
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, WoodRackEntity blockEntity) {
        if (level.isClientSide()) return;

        if (!blockEntity.hasItemInSlot()) {
            blockEntity.resetProgress();
            return;
        }

        Optional<RecipeHolder<RackRecipe>> recipe = blockEntity.getRecipe();
        if (recipe.isPresent()) {
            blockEntity.processRecipe(recipe.get().value());
        } else {
            blockEntity.resetProgress();
        }
    }

    private boolean hasItemInSlot() {
        return !itemHandler.getStackInSlot(0).isEmpty();
    }

    private Optional<RecipeHolder<RackRecipe>> getRecipe() {
        if (this.level == null) return Optional.empty();

        ItemStack inputStack = itemHandler.getStackInSlot(0);
        SingleRecipeInput recipeInput = new SingleRecipeInput(inputStack);

        return this.level.getRecipeManager().getRecipeFor(
                ModRecipes.RACK_TYPE.get(),
                recipeInput,
                this.level
        );
    }

    private void processRecipe(RackRecipe recipe) {
        dryingTime = recipe.getDryingTime();
        processTime++;

        if (processTime >= dryingTime) {
            craftItem(recipe);
        }
    }

    private void craftItem(RackRecipe recipe) {
        if (this.level == null) return;

        ItemStack inputStack = itemHandler.getStackInSlot(0);
        SingleRecipeInput recipeInput = new SingleRecipeInput(inputStack);
        ItemStack result = recipe.assemble(recipeInput, this.level.registryAccess());

        itemHandler.setStackInSlot(0, result);
        resetProgress();
        this.setChanged();
    }

    private void resetProgress() {
        processTime = 0;
    }

    public void clear() {
        itemHandler.setStackInSlot(0, ItemStack.EMPTY);
    }

    public ItemStack getStack() {
        return this.itemHandler.getStackInSlot(0);
    }

    public void setStack(ItemStack stack) {
        this.itemHandler.setStackInSlot(0, stack);
        this.processTime = 0;
        setChanged();
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, provider);
        return tag;
    }

    public void drops(BlockPos pos) {
        ItemStack stack = itemHandler.getStackInSlot(0);
        if (!stack.isEmpty()) {
            Containers.dropItemStack(this.level, pos.getX(), pos.getY(), pos.getZ(), stack);
        }
    }

    public boolean isEmpty() {
        return this.getStack().isEmpty();
    }
}
