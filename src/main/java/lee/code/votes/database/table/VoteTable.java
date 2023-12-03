package lee.code.votes.database.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@DatabaseTable(tableName = "votes")
public class VoteTable {
  @DatabaseField(id = true, canBeNull = false)
  private UUID uniqueId;

  @DatabaseField(columnName = "votes")
  private int votes;

  public VoteTable(UUID uniqueId) {
    this.uniqueId = uniqueId;
  }
}
