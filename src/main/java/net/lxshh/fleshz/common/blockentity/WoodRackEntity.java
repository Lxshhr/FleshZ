package net.lxshh.fleshz.common.blockentity;

import net.lxshh.fleshz.common.recipes.ModRecipes;
import net.lxshh.fleshz.common.recipes.RackRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public WoodRackEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.WOOD_RACK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.putInt("drying_time", dryingTime);
        tag.putInt("process_time", processTime);
        tag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.dryingTime = tag.getInt("drying_time");
        this.processTime = tag.getInt("process_time");
        itemHandler.deserializeNBT(tag.getCompound("inventory"));
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, WoodRackEntity blockEntity) {
        if (level.isClientSide()) return;

        if (!blockEntity.hasItemInSlot()) {
            blockEntity.resetProgress();
            return;
        }

        Optional<RackRecipe> recipe = blockEntity.getRecipe();
        if (recipe.isPresent()) {
            blockEntity.processRecipe(recipe.get());
        } else {
            blockEntity.resetProgress();
        }
    }

    private boolean hasItemInSlot() {
        return !itemHandler.getStackInSlot(0).isEmpty();
    }

    private Optional<RackRecipe> getRecipe() {
        if (this.level == null) return Optional.empty();

        ItemStack inputStack = itemHandler.getStackInSlot(0);
        SimpleContainer recipeInput = new SimpleContainer(inputStack);

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
        SimpleContainer recipeInput = new SimpleContainer(inputStack);
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
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }

    public void drops(BlockPos pos) {
        ItemStack stack = itemHandler.getStackInSlot(0);
        if (!stack.isEmpty()) {
            Containers.dropItemStack(this.level, pos.getX(), pos.getY(), pos.getZ(), stack);
        }
    }
}
