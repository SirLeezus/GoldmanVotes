package lee.code.votes.database.handlers;

import lee.code.votes.database.DatabaseManager;
import lee.code.votes.database.table.VoteTable;

public class DatabaseHandler {
  private final DatabaseManager databaseManager;

  public DatabaseHandler(DatabaseManager databaseManager) {
    this.databaseManager = databaseManager;
  }

  public void updateVoteDatabase(VoteTable voteTable) {
    databaseManager.updateVoteTable(voteTable);
  }

  public void createVoteDatabase(VoteTable voteTable) {
    databaseManager.createVoteTable(voteTable);
  }

  public void deleteChunkDatabase(VoteTable voteTable) {
    databaseManager.deleteVoteTable(voteTable);
  }
}
