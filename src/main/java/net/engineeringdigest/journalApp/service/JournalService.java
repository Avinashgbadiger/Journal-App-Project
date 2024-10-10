package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.model.JournalEntry;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface JournalService {

    JournalEntry savingJournalByUsername(JournalEntry journalEntry,String username);

     JournalEntry creatingJournalEntry(JournalEntry journalEntry );

    Optional<JournalEntry> getJournalByUser(ObjectId id,String username);


    JournalEntry updateById(ObjectId id,JournalEntry journalEntry);

    List<JournalEntry>  getAll(String username);

    void deleteById(ObjectId id, String username);
}
