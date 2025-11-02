package com.github.justadeni.logically.api;
import org.bukkit.entity.Player;

public class PlayerManager implements LogicallyAPI {

    protected static LogicallyAPI get() {
        return null;
    }

    private PlayerManager() {}

    @Override
    public boolean isEnabled(Player player) {
        return false;
    }

    @Override
    public void set(Player player, boolean state) {

    }

}
