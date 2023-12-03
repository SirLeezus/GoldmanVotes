package lee.code.votes.commands.cmds;

import lee.code.votes.commands.SubCommand;
import lee.code.votes.lang.Lang;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class WebsitesCMD extends SubCommand {

  @Override
  public String getName() {
    return "websites";
  }

  @Override
  public String getDescription() {
    return "A list of websites you can vote on.";
  }

  @Override
  public String getSyntax() {
    return "/vote websites";
  }

  @Override
  public String getPermission() {
    return "votes.command.websites";
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
    final List<Component> lines = new ArrayList<>();
    lines.add(Lang.COMMAND_WEBSITES_TITLE.getComponent(null));
    lines.add(Component.text(""));
    lines.add(Lang.COMMAND_WEBSITE_ONE.getComponent(null).hoverEvent(Lang.COMMAND_WEBSITE_HOVER.getComponent(null)).clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, Lang.COMMAND_WEBSITE_ONE_LINK.getString())));
    lines.add(Lang.COMMAND_WEBSITE_TWO.getComponent(null).hoverEvent(Lang.COMMAND_WEBSITE_HOVER.getComponent(null)).clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, Lang.COMMAND_WEBSITE_TWO_LINK.getString())));
    lines.add(Component.text(""));
    lines.add(Lang.COMMAND_WEBSITES_FOOTER.getComponent(null));
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
