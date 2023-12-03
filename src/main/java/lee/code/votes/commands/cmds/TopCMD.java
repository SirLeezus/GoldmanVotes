package lee.code.votes.commands.cmds;

import lee.code.colors.ColorAPI;
import lee.code.playerdata.PlayerDataAPI;
import lee.code.votes.Votes;
import lee.code.votes.commands.SubCommand;
import lee.code.votes.lang.Lang;
import lee.code.votes.utils.CoreUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class TopCMD extends SubCommand {
  private final Votes votes;

  public TopCMD(Votes votes) {
    this.votes = votes;
  }

  @Override
  public String getName() {
    return "top";
  }

  @Override
  public String getDescription() {
    return "The server's top voting leaderboard.";
  }

  @Override
  public String getSyntax() {
    return "/vote top &f<page>";
  }

  @Override
  public String getPermission() {
    return "votes.command.top";
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
    final Map<UUID, Integer> sortedBalances = CoreUtil.sortByValue(votes.getCacheManager().getCacheVotes().getVoteData().getAllVotes(), Comparator.reverseOrder());
    final ArrayList<UUID> players = new ArrayList<>(sortedBalances.keySet());
    int index;
    int page = 0;
    final int maxDisplayed = 10;
    if (args.length > 0) {
      if (CoreUtil.isPositiveIntNumber(args[0])) page = Integer.parseInt(args[0]);
    }
    int position = page * maxDisplayed + 1;
    final ArrayList<Component> lines = new ArrayList<>();
    lines.add(Lang.COMMAND_TOP_TITLE.getComponent(null));
    lines.add(Component.text(" "));

    for (int i = 0; i < maxDisplayed; i++) {
      index = maxDisplayed * page + i;
      if (index >= players.size()) break;
      final UUID targetID = players.get(index);
      lines.add(Lang.COMMAND_TOP_LINE.getComponent(new String[]{
        String.valueOf(position),
        ColorAPI.getNameColor(targetID, PlayerDataAPI.getName(targetID)),
        CoreUtil.parseValue(sortedBalances.get(targetID))
      }));
      position++;
    }

    if (lines.size() == 2) return;
    lines.add(Component.text(" "));
    lines.add(CoreUtil.createPageSelectionComponent("/vote top", page));
    for (Component line : lines) player.sendMessage(line);
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
