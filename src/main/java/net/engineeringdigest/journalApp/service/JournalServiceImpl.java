package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.model.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Override
    public JournalEntry creating(JournalEntry journalEntry) {
        JournalEntry save = this.journalRepository.save(journalEntry);
        return save;
    }

    @Override
    public Optional<JournalEntry> getById(ObjectId id) {
        Optional<JournalEntry> byId = this.journalRepository.findById(id);
        return byId;
    }

    @Override
    public JournalEntry updateById(ObjectId id, JournalEntry journalEntry) {
        JournalEntry byId = this.journalRepository.findById(id).orElse(null);
        if (byId != null) {
            byId.setTitle(journalEntry.getTitle() != null && !journalEntry.getTitle().equals("") ?
                    journalEntry.getTitle() : byId.getTitle());
            byId.setContent(journalEntry.getContent() != null && !journalEntry.getContent().equals("") ? journalEntry.getContent() : byId.getContent());
            return this.journalRepository.save(byId);
        } else {
            return null;
        }


    }

    @Override
    public List<JournalEntry> getAll() {
        List<JournalEntry> all = this.journalRepository.findAll();
        return all;
    }

    @Override
    public void deleteById(ObjectId id) {
        this.journalRepository.deleteById(id);

    }
}
