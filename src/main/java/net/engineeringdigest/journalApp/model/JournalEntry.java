package net.engineeringdigest.journalApp.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "journal_entry")
@Data
@NoArgsConstructor
public class JournalEntry {

    @Id
    private ObjectId id;

    private String title;

    private String content;
}
