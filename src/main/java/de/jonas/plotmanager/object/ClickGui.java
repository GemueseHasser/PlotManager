package de.jonas.plotmanager.object;

import de.jonas.PlotManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

/**
 * Ein {@link ClickGui} stellt eine anklickbare grafische Benutzeroberfläche in Form eines {@link Inventory Inventars}
 * dar.
 */
@NotNull
public abstract class ClickGui implements Listener {

    //<editor-fold desc="LOCAL FIELDS">
    /** Das Inventar, auf dem dieses {@link ClickGui aufbaut.} */
    @Getter
    private final Inventory inventory;
    /** Die schwarzen Glasscheiben, die die Umrandung des Gui darstellen. */
    @NotNull
    private final ItemStack blackGlass = ItemCreator.getStack(
        "",
        Material.BLACK_STAINED_GLASS_PANE,
        Collections.emptyList()
    );
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt mithilfe eines Titels und Items eine neue Instanz eines {@link ClickGui}. Ein {@link ClickGui} stellt
     * eine anklickbare grafische Benutzeroberfläche in Form eines {@link Inventory Inventars} dar.
     *
     * @param items Die Items, die diesem {@link ClickGui} hinzugefügt werden sollen.
     * @param title Der Titel dieses {@link ClickGui}.
     */
    public ClickGui(
        @NotNull final ItemStack[] items,
        @NotNull final String title
    ) {
        // calculate inventory size
        final int finalItemLength = items.length + ((items.length / 9) * 2);
        final int invSize = finalItemLength + (9 - finalItemLength % 9) + 18;

        // create inventory
        this.inventory = Bukkit.createInventory(null, invSize, ChatColor.GRAY + title);

        // fill inventory
        int itemCounter = 0;

        loop:
        for (int i = 10; i < invSize; i += 9) {
            for (int j = 0; j < 7; j++) {
                if (itemCounter >= items.length) break loop;

                this.inventory.setItem(i + j, items[itemCounter]);

                itemCounter++;
            }
        }

        for (int i = 0; i < 9; i++) {
            this.inventory.setItem(i, blackGlass);
        }

        for (int i = invSize - 1; i > invSize - 9; i--) {
            this.inventory.setItem(i, blackGlass);
        }

        for (int i = 9; i < invSize; i += 9) {
            this.inventory.setItem(i, blackGlass);
        }

        for (int i = 17; i < invSize; i += 9) {
            this.inventory.setItem(i, blackGlass);
        }

        // register this listener
        Bukkit.getPluginManager().registerEvents(this, PlotManager.getInstance());
    }
    //</editor-fold>


    /**
     * Mithilfe dieser Methode wird das Anklicken eines Items (nicht die Umrandung) verarbeitet.
     *
     * @param item Das Item, welches angeklickt wurde.
     */
    public abstract void handleClick(@NotNull final ItemStack item);


    //<editor-fold desc="implementation">
    @EventHandler
    public void onInventoryClick(@NotNull final InventoryClickEvent e) {
        // check inventory
        if (!e.getInventory().equals(this.inventory)) return;
        if (e.getCurrentItem() == null) return;

        // cancel event
        e.setCancelled(true);

        if (e.getCurrentItem().equals(this.blackGlass)) return;

        // handle click
        handleClick(e.getCurrentItem());
    }

    @EventHandler
    public void onInteractInventory(@NotNull final InventoryInteractEvent e) {
        // check inventory
        if (!e.getInventory().equals(this.inventory)) return;

        // cancel event
        e.setCancelled(true);
    }
    //</editor-fold>

}
