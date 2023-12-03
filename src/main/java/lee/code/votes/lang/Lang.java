package lee.code.votes.lang;

import lee.code.votes.utils.CoreUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.kyori.adventure.text.Component;

@AllArgsConstructor
public enum Lang {
  PREFIX("&a&lVotes &6➔ "),
  USAGE("&6&lUsage: &e{0}"),
  NEXT_PAGE_TEXT("&2&lNext &a&l>>&a---------"),
  PREVIOUS_PAGE_TEXT("&a---------&a&l<< &2&lPrev"),
  PAGE_SPACER_TEXT(" &e| "),
  NEXT_PAGE_HOVER("&6&lNext Page"),
  PREVIOUS_PAGE_HOVER("&6&lPrevious Page"),
  COMMAND_WEBSITE_HOVER("&bClick to open website!"),
  COMMAND_HELP_TITLE("&a--------- &7[ &2&lVote Help &7] &a---------"),
  COMMAND_HELP_SUB_COMMAND("&3{0}&b. &e{1}"),
  COMMAND_HELP_SUB_COMMAND_HOVER("&6{0}"),
  COMMAND_HELP_DIVIDER("&a--------------------------------"),
  COMMAND_WEBSITES_TITLE("&a---- &e[ &2&lVote Websites &e] &a----"),
  COMMAND_WEBSITE_ONE("&31&b. &6&lMojang &a(Coming soon!)"),
  COMMAND_WEBSITE_ONE_LINK("https://findmcserver.com/server/NmEpUub630"),
  COMMAND_WEBSITE_TWO("&32&b. &6&lPlanet Minecraft"),
  COMMAND_WEBSITE_TWO_LINK("https://www.planetminecraft.com/server/journey-towns"),
  COMMAND_WEBSITES_FOOTER("&a--------------------------"),
  PLAYER_VOTED("&2The player {0} &2voted for the server and was rewarded &6${1}&2!"),
  COMMAND_TOTAL_SUCCESSFUL("&2You have voted for the server &6{0} &2times!"),
  COMMAND_TOP_TITLE("&a------ &e[ &2&lVote Leaderboard &e] &a------"),
  COMMAND_TOP_LINE("&3{0}&7. {1} &7| &a{2}"),
  ERROR_NO_PERMISSION("&cYou do not have permission for this."),
  ERROR_ONE_COMMAND_AT_A_TIME("&cYou're currently processing another command, please wait for it to finish."),
  ERROR_NOT_CONSOLE_COMMAND("&cThis command does not work in console."),

  ;
  @Getter private final String string;

  public String getString(String[] variables) {
    String value = string;
    if (variables == null || variables.length == 0) return value;
    for (int i = 0; i < variables.length; i++) value = value.replace("{" + i + "}", variables[i]);
    return value;
  }

  public Component getComponent(String[] variables) {
    String value = string;
    if (variables == null || variables.length == 0) return CoreUtil.parseColorComponent(value);
    for (int i = 0; i < variables.length; i++) value = value.replace("{" + i + "}", variables[i]);
    return CoreUtil.parseColorComponent(value);
  }
}
