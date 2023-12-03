package lee.code.votes.listeners;

import com.vexsoftware.votifier.model.VotifierEvent;
import lee.code.colors.ColorAPI;
import lee.code.economy.EcoAPI;
import lee.code.playerdata.PlayerDataAPI;
import lee.code.votes.Votes;
import lee.code.votes.lang.Lang;
import lee.code.votes.utils.CoreUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class VoteListener implements Listener {
  private final Votes votes;

  public VoteListener(Votes votes) {
    this.votes = votes;
  }

  @EventHandler
  public void onVote(VotifierEvent e) {
    final String voter = e.getVote().getUsername();
    final UUID voterID = PlayerDataAPI.getUniqueId(voter);
    if (voterID == null) return;
    votes.getCacheManager().getCacheVotes().getVoteData().addVote(voterID);
    EcoAPI.addBalance(voterID, 1000);
    Bukkit.getServer().sendMessage(Lang.PREFIX.getComponent(null).append(Lang.PLAYER_VOTED.getComponent(new String[]{ColorAPI.getNameColor(voterID, voter), CoreUtil.parseValue(1000)})));
  }
}
