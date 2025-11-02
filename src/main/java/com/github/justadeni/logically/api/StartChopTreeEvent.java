package com.github.justadeni.logically.api;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.joml.Vector3f;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Called when a player begins chopping a tree.
 * <p>
 * This event provides detailed information about the detected tree structure,
 * including logs, leaves, materials, height, player and the fall direction.
 * <p>
 * It runs asynchronously, so modifying world state without proper synchronization is not allowed.
 * It can be cancelled, in which case the single log will break as if in vanilla.
 * <p>
 * Mutable properties are logs, leaves and fall axis. The rest is read-only.
 */
public final class StartChopTreeEvent extends Event implements Cancellable {

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

    /** The set of all log blocks that make up the tree. */
    private final Set<Block> logs;

    /** The set of all leaf blocks that make up the tree. */
    private final Set<Block> leaves;

    /** The normalized fall axis of the tree, usually the 'heaviest' direction. */
    private final Vector3f axis;

    /** The player who initiated the chop. */
    private final Player player;

    /** The lowest average location of the tree. */
    private final Location root;

    /** The world in which the chop occurs. */
    private final World world;

    /** The total detected height of the tree in blocks. */
    private final int height;

    /** The set of materials identified as valid log types in this tree. */
    private final Set<Material> logMaterials;

    /** The set of materials identified as valid leaf types in this tree. */
    private final Set<Material> leavesMaterials;

    /**
     * Constructs a new {@code StartChopTreeEvent}.
     *
     * @param logs            the detected log blocks
     * @param leaves          the detected leaf blocks
     * @param axis            the main tree axis vector
     * @param player          the player who started chopping
     * @param root            the base block of the tree
     * @param world           the world where the event occurred
     * @param height          the total height of the tree
     * @param logMaterials    the log materials in this tree
     * @param leavesMaterials the leaf materials in this tree
     */
    public StartChopTreeEvent(Set<Block> logs, Set<Block> leaves, Vector3f axis, Player player, Location root, World world, int height, Set<Material> logMaterials, Set<Material> leavesMaterials) {
        super(true);
        this.logs = ConcurrentHashMap.newKeySet();
        this.logs.addAll(logs);
        this.leaves = ConcurrentHashMap.newKeySet();
        this.leaves.addAll(leaves);
        this.axis = new Vector3f(axis);
        this.player = player;
        this.root = new Location(root.getWorld(), root.x(), root.y(), root.z());
        this.world = world;
        this.height = height;
        this.logMaterials = Set.copyOf(logMaterials);
        this.leavesMaterials = Set.copyOf(leavesMaterials);
    }

    /**
     * Gets all log blocks that form the tree.
     * Modifying the underlying set is permitted, but caution is advised: multiple event listeners may concurrently mutate it.
     *
     * @return the set of log blocks
     */
    public Set<Block> getLogs() {
        return logs;
    }

    /**
     * Gets all leaf blocks that form the tree.
     * Modifying the underlying set is permitted, but caution is advised: multiple event listeners may concurrently mutate it.
     *
     * @return the set of leaf blocks
     */
    public Set<Block> getLeaves() {
        return leaves;
    }

    /**
     * Gets the normalized fall axis of the tree.
     * Modifying the Vector3f is allowed.
     *
     * @return the axis vector
     */
    public Vector3f getAxis() {
        return axis;
    }

    /**
     * Gets the player who started chopping the tree.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the pivot location for tree fall.
     *
     * @return the root location
     */
    public Location getRoot() {
        return root;
    }

    /**
     * Gets the world where the event occurred.
     *
     * @return the world
     */
    public World getWorld() {
        return world;
    }

    /**
     * Gets the total detected height of the tree.
     *
     * @return the tree height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the materials that represent potential logs in this tree type.
     * Cannot be modified.
     *
     * @return the set of log materials
     */
    public Set<Material> getLogMaterials() {
        return logMaterials;
    }

    /**
     * Gets the materials that represent potential leaves in this tree type.
     * Cannot be modified.
     *
     * @return the set of leaf materials
     */
    public Set<Material> getLeavesMaterials() {
        return leavesMaterials;
    }
}

