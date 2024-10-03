package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.model.JournalEntry;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface JournalService {

    JournalEntry creating(JournalEntry journalEntry);

    Optional<JournalEntry> getById(ObjectId id);

    JournalEntry updateById(ObjectId id,JournalEntry journalEntry);

    List<JournalEntry>  getAll();

    void deleteById(ObjectId id);
}
