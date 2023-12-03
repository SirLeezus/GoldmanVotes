package lee.code.votes.database.cache.data;
import lee.code.votes.database.cache.CacheVotes;
import lee.code.votes.database.table.VoteTable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class VoteData {
  private final CacheVotes cacheVotes;
  private final ConcurrentHashMap<UUID, Integer> voteDataCache = new ConcurrentHashMap<>();

  public VoteData(CacheVotes cacheVotes) {
    this.cacheVotes = cacheVotes;
  }

  public void cacheVotes(VoteTable voteTable) {
    voteDataCache.put(voteTable.getUniqueId(), voteTable.getVotes());
  }

  public void setVoteAmount(UUID uuid, int amount) {
    voteDataCache.put(uuid, amount);
    final VoteTable voteTable = cacheVotes.getVoteTable(uuid);
    voteTable.setVotes(amount);
    cacheVotes.updateVoteDatabase(voteTable);
  }

  public int getTotalVotes(UUID uuid) {
    if (voteDataCache.containsKey(uuid)) return voteDataCache.get(uuid);
    return 0;
  }

  public void addVote(UUID uuid) {
    setVoteAmount(uuid, getTotalVotes(uuid) + 1);
  }

  public Map<UUID, Integer> getAllVotes() {
    return new HashMap<>(voteDataCache);
  }
}
