package lee.code.votes.database;

import lee.code.votes.Votes;
import lee.code.votes.database.cache.CacheVotes;
import lombok.Getter;

public class CacheManager {
  private final Votes votes;
  @Getter private final CacheVotes cacheVotes;

  public CacheManager(Votes votes, DatabaseManager databaseManager) {
    this.votes = votes;
    this.cacheVotes = new CacheVotes(databaseManager);
  }
}
