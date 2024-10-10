package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.model.JournalEntry;
import net.engineeringdigest.journalApp.model.Users;
import net.engineeringdigest.journalApp.repository.JournalRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public JournalEntry savingJournalByUsername(JournalEntry journalEntry,String username) {
        Users byUsername = this.userRepository.findByUsername(username);

        try {
            JournalEntry save = this.journalRepository.save(journalEntry);
            byUsername.getJournalEntries().add(save);
            this.userRepository.save(byUsername);
            return save;

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public JournalEntry creatingJournalEntry(JournalEntry journalEntry ) {


        try {
          return  this.journalRepository.save(journalEntry);


        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<JournalEntry> getJournalByUser(ObjectId id, String username) {
        Users user = this.userRepository.findByUsername(username);

        if (user != null) {
          return user.getJournalEntries().stream().filter(e -> e.getId().equals(id)).findAny();

        }

        return Optional.empty();  // Return an empty Optional if the user is not found
    }


    @Override
    public JournalEntry updateById(ObjectId id, JournalEntry journalEntry) {
        JournalEntry byId = this.journalRepository.findById(id).orElse(null);
        if (byId != null) {
            byId.setTitle(journalEntry.getTitle() != null && !journalEntry.getTitle().equals("") ?
                    journalEntry.getTitle() : byId.getTitle());
            byId.setContent(journalEntry.getContent() != null && !journalEntry.getContent().equals("") ? journalEntry.getContent() : byId.getContent());
            return this.creatingJournalEntry(byId);
        } else {
            return null;
        }


    }

    @Override
    public List<JournalEntry> getAll(String username) {
        Users byUsername = this.userRepository.findByUsername(username);
        List<JournalEntry> journalEntries = byUsername.getJournalEntries();
        if(journalEntries!=null)
        {
            return journalEntries;
        }
        return null;
    }

    @Override
    public void deleteById(ObjectId id, String username) {
        Users byUsername = this.userRepository.findByUsername(username);
        byUsername.getJournalEntries().removeIf(e -> e.getId().equals(id));
        this.userRepository.save(byUsername);
        this.journalRepository.deleteById(id);

    }
}
