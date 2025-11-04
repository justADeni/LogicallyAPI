package com.github.justadeni.logically.api;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public record Drop(Location location, ItemStack itemStack) {}
