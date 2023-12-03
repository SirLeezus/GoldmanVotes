package lee.code.votes.commands.cmds;

import lee.code.votes.Votes;
import lee.code.votes.commands.SubCommand;
import lee.code.votes.lang.Lang;
import lee.code.votes.utils.CoreUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TotalCMD extends SubCommand {
  private final Votes votes;

  public TotalCMD(Votes votes) {
    this.votes = votes;
  }

  @Override
  public String getName() {
    return "total";
  }

  @Override
  public String getDescription() {
    return "The total amount of times you voted for the server.";
  }

  @Override
  public String getSyntax() {
    return "/vote total";
  }

  @Override
  public String getPermission() {
    return "votes.command.total";
  }

  @Override
  public boolean performAsync() {
    return true;
  }

  @Override
  public boolean performAsyncSynchronized() {
    return false;
  }

  @Override
  public void perform(Player player, String[] args) {
    player.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.COMMAND_TOTAL_SUCCESSFUL.getComponent(new String[]{CoreUtil.parseValue(votes.getCacheManager().getCacheVotes().getVoteData().getTotalVotes(player.getUniqueId()))})));
  }

  @Override
  public void performConsole(CommandSender console, String[] args) {
    console.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.ERROR_NOT_CONSOLE_COMMAND.getComponent(null)));
  }

  @Override
  public void performSender(CommandSender sender, String[] args) {
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, String[] args) {
    return new ArrayList<>();
  }
}
