package de.jonas.plotmanager.object;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Mithilfe des {@link ItemCreator} lässt sich ein {@link ItemStack} ohne Probleme erzeugen.
 */
public final class ItemCreator {

    //<editor-fold desc="utility">

    /**
     * Erzeugt mithilfe eines Namens, eines Materials und einer Beschreibung einen neuen {@link ItemStack} und gibt
     * diesen zurück, um das Erzeugen eines {@link ItemStack} zu erleichtern.
     *
     * @param name     Der Name des {@link ItemStack}.
     * @param material Das {@link Material}, woraus der {@link ItemStack} bestehen soll.
     * @param lore     Die Beschreibung des {@link ItemStack}, wobei jeder Eintrag der Liste eine neue Zeile der
     *                 Beschreibung darstellt.
     *
     * @return Einen neuen {@link ItemStack}, der aus verschiedenen Eigenschaften generiert wurde.
     */
    public static ItemStack getStack(
        @NotNull final String name,
        @NotNull final Material material,
        @NotNull final List<String> lore
    ) {
        final ItemStack stack = new ItemStack(material);
        final ItemMeta meta = stack.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.GOLD + name);
        meta.setLore(lore);
        stack.setItemMeta(meta);

        return stack;
    }
    //</editor-fold>

}
