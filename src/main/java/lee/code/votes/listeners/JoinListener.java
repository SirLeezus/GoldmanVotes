package lee.code.votes.listeners;

import lee.code.votes.Votes;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
  private final Votes votes;

  public JoinListener(Votes votes) {
    this.votes = votes;
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent e) {
    if (!votes.getCacheManager().getCacheVotes().hasPlayerData(e.getPlayer().getUniqueId())) {
      votes.getCacheManager().getCacheVotes().createPlayerData(e.getPlayer().getUniqueId());
    }
  }
}
