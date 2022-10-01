package de.jonas;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * <p>Die Haupt- und Main-Klasse dieses Plugins, durch die das gesamte Plugin initialisiert wird. Der
 * {@link PlotManager} ist die Klasse, in der alle Unterinstanzen registriert werden.</p>
 *
 * <p>Der {@link PlotManager} stellt ein Interface zur Verfügung, mit dem ein Spieler seine Plots verwalten und
 * Einstellungen an seinen Plots vornehmen kann.</p>
 */
public final class PlotManager extends JavaPlugin {

    /** Die Instanz dieses Plugins. */
    @Getter
    private static PlotManager instance;


    //<editor-fold desc="setup and start">
    @Override
    public void onEnable() {
        super.onEnable();

        // initialize plugin instance
        instance = this;
    }
    //</editor-fold>

    //<editor-fold desc="shutdown">
    @Override
    public void onDisable() {
        super.onDisable();
    }
    //</editor-fold>

}
