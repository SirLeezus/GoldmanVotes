package lee.code.votes;

import com.mojang.brigadier.tree.LiteralCommandNode;
import lee.code.votes.commands.CommandManager;
import lee.code.votes.commands.TabCompletion;
import lee.code.votes.database.CacheManager;
import lee.code.votes.database.DatabaseManager;
import lee.code.votes.listeners.JoinListener;
import lee.code.votes.listeners.VoteListener;
import lombok.Getter;
import me.lucko.commodore.CommodoreProvider;
import me.lucko.commodore.file.CommodoreFileReader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Votes extends JavaPlugin {
  @Getter private CommandManager commandManager;
  @Getter private CacheManager cacheManager;
  private DatabaseManager databaseManager;

  @Override
  public void onEnable() {
    this.commandManager = new CommandManager(this);
    this.databaseManager = new DatabaseManager(this);
    this.cacheManager = new CacheManager(this, databaseManager);
    registerCommands();
    registerListeners();
    databaseManager.initialize(false);
  }

  @Override
  public void onDisable() {
    databaseManager.closeConnection();
  }

  private void registerCommands() {
    getCommand("vote").setExecutor(commandManager);
    getCommand("vote").setTabCompleter(new TabCompletion(commandManager));
    loadCommodoreData();
  }

  private void registerListeners() {
    getServer().getPluginManager().registerEvents(new JoinListener(this), this);
    getServer().getPluginManager().registerEvents(new VoteListener(this), this);
  }

  private void loadCommodoreData() {
    try {
      final LiteralCommandNode<?> towns = CommodoreFileReader.INSTANCE.parse(getResource("vote.commodore"));
      CommodoreProvider.getCommodore(this).register(getCommand("vote"), towns);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
