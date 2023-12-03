package lee.code.votes.database.cache;

import lee.code.votes.database.DatabaseManager;
import lee.code.votes.database.cache.data.VoteData;
import lee.code.votes.database.handlers.DatabaseHandler;
import lee.code.votes.database.table.VoteTable;
import lombok.Getter;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CacheVotes extends DatabaseHandler {
  @Getter private final VoteData voteData;
  private final ConcurrentHashMap<UUID, VoteTable> votesCache = new ConcurrentHashMap<>();

  public CacheVotes(DatabaseManager databaseManager) {
    super(databaseManager);
    this.voteData = new VoteData(this);
  }

  public VoteTable getVoteTable(UUID uuid) {
    return votesCache.get(uuid);
  }

  public void setVoteTable(VoteTable voteTable) {
    votesCache.put(voteTable.getUniqueId(), voteTable);
    voteData.cacheVotes(voteTable);
  }

  public void createPlayerData(UUID uuid) {
    final VoteTable voteTable = new VoteTable(uuid);
    setVoteTable(voteTable);
    createVoteDatabase(voteTable);
  }

  public boolean hasPlayerData(UUID uuid) {
    return votesCache.containsKey(uuid);
  }
}
