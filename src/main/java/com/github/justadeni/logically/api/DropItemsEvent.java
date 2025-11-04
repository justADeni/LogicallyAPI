package com.github.justadeni.logically.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Called after a tree falls down and drops are calculated, but before they're dropped.
 * <p>
 * It runs asynchronously, so modifying world state without proper synchronization is not allowed.
 * It can be cancelled, in which case nothing will drop.
 * <p>
 * Mutable property is List of Drops. The rest is read-only.
 */
public final class DropItemsEvent extends Event implements Cancellable {

    /** Handler list required by the Bukkit event system. */
    private static final HandlerList HANDLERS = new HandlerList();

    /**
     * Gets the list of handlers for this event.
     *
     * @return the handler list
     */
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    /** Whether this event is cancelled. */
    private boolean cancelled = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    private final List<Drop> dropList;
    private final Block origin;
    private final World world;
    private final Player player;

    public DropItemsEvent(List<Drop> dropList, Block origin, World world, Player player) {
        this.dropList = new CopyOnWriteArrayList<>(dropList);
        this.origin = origin;
        this.world = world;
        this.player = player;
    }

    /**
     * Gets the List of Drops, where each {@link Drop} is record containing {@link Location} and {@link ItemStack}
     *
     * @return the {@link List<Drop>}
     */
    public List<Drop> getDropList() {
        return dropList;
    }

    /**
     * Gets the player who started chopping the tree.
     *
     * @return the {@link Player}
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the broken block that caused the tree to fall.
     *
     * @return the origin {@link Block}
     */
    public Block getOrigin() {
        return origin;
    }

    /**
     * Gets the world where the event occurred.
     *
     * @return the {@link World}
     */
    public World getWorld() {
        return world;
    }

}
