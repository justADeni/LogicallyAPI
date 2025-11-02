package com.github.justadeni.logically.api;

import org.bukkit.entity.Player;

/**
 * The main API interface for the Logically plugin, which controls whether players
 * have tree chopping enabled or disabled.
 * <p>
 * Provides methods to check and modify the chopping state of individual {@link Player}s.
 * The active API instance can be accessed via {@link #get()}.
 */
public interface LogicallyAPI {

    /**
     * Returns the active instance of the Logically API.
     *
     * @return the current {@link LogicallyAPI} implementation
     */
    static LogicallyAPI get() {
        return PlayerManager.get();
    }

    /**
     * Checks whether tree chopping is enabled for the given player.
     *
     * @param player the player to check
     * @return {@code true} if tree chopping is enabled, {@code false} otherwise
     */
    boolean isEnabled(Player player);

    /**
     * Enables or disables tree chopping for the given player.
     *
     * @param player the player to modify
     * @param state {@code true} to enable chopping, {@code false} to disable it
     */
    void set(Player player, boolean state);
}

