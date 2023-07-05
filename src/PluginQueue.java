
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


public class PluginQueue extends JavaPlugin implements Listener {

    private ArrayList <Player> Coda= new ArrayList<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Il plugin della coda è abilitato!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Il plugin della coda è disabilitato!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("arena")) {

            Player p1 = (Player) sender;
            Coda.add(p1);
            p1.sendMessage(ChatColor.GREEN + "Sei stato aggiunto alla coda.");

            return true;
        }
        return false;
    }
    private void Teletrasporto(Player p1) {
        double x= 4530.53;
        double y=58.87;
        double z=2749.76;
        Location teletrasporto = new Location(p1.getWorld(), x, y, z);
        p1.teleport(teletrasporto);
        p1.sendMessage(ChatColor.AQUA + "Sei stato teletrasportato in arena.");
    }

    @EventHandler
    public void Verifica(PlayerJoinEvent event) {
        if (Coda.isEmpty()) {
            return;
        }
        for (int i=0; i<Coda.size(); i++) {
            if (Coda.get(i).equals(event.getPlayer())){
                event.getPlayer().sendMessage(ChatColor.AQUA + "Ti trovi nella posizione numero " + i);
            }
        }


        if (Coda.get(0).equals(event.getPlayer())) {

            Teletrasporto(event.getPlayer());
            Coda.remove(0);
        }
    }
}

