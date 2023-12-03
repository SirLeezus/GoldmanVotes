package lee.code.votes.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.jdbc.db.DatabaseTypeUtils;
import com.j256.ormlite.logger.LogBackendType;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lee.code.votes.Votes;
import lee.code.votes.database.table.VoteTable;
import org.bukkit.Bukkit;

import java.io.File;
import java.sql.SQLException;
import java.util.UUID;

public class DatabaseManager {
  private final Votes votes;
  private final Object synchronizedThreadLock = new Object();
  private Dao<VoteTable, UUID> voteDao;
  private ConnectionSource connectionSource;

  public DatabaseManager(Votes votes) {
    this.votes = votes;
  }

  private String getDatabaseURL() {
    if (!votes.getDataFolder().exists()) votes.getDataFolder().mkdir();
    return "jdbc:sqlite:" + new File(votes.getDataFolder(), "database.db");
  }

  public void initialize(boolean debug) {
    if (!debug) LoggerFactory.setLogBackendFactory(LogBackendType.NULL);
    try {
      final String databaseURL = getDatabaseURL();
      connectionSource = new JdbcConnectionSource(
        databaseURL,
        "test",
        "test",
        DatabaseTypeUtils.createDatabaseType(databaseURL));
      createOrCacheTables();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void closeConnection() {
    try {
      connectionSource.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void createOrCacheTables() throws SQLException {
    final CacheManager cacheManager = votes.getCacheManager();

    //Vote data
    TableUtils.createTableIfNotExists(connectionSource, VoteTable.class);
    voteDao = DaoManager.createDao(connectionSource, VoteTable.class);

    for (VoteTable voteTable : voteDao.queryForAll()) {
      cacheManager.getCacheVotes().setVoteTable(voteTable);
    }
  }

  public void createVoteTable(VoteTable voteTable) {
    synchronized (synchronizedThreadLock) {
      Bukkit.getAsyncScheduler().runNow(votes, scheduledTask -> {
        try {
          voteDao.createIfNotExists(voteTable);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      });
    }
  }

  public void updateVoteTable(VoteTable voteTable) {
    synchronized (synchronizedThreadLock) {
      Bukkit.getAsyncScheduler().runNow(votes, scheduledTask -> {
        try {
          voteDao.update(voteTable);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      });
    }
  }

  public void deleteVoteTable(VoteTable voteTable) {
    synchronized (synchronizedThreadLock) {
      Bukkit.getAsyncScheduler().runNow(votes, scheduledTask -> {
        try {
          voteDao.delete(voteTable);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      });
    }
  }
}
