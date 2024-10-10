package net.engineeringdigest.journalApp.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class Users {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String username;

    private String password;

    @DBRef
    private List<JournalEntry> journalEntries=new ArrayList<>();
}
