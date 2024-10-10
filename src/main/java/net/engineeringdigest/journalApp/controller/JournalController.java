package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.model.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class JournalController {

    @Autowired
    @Qualifier(value = "journalServiceImpl")
    private JournalService journalService;

    @PostMapping("{username}")
    public ResponseEntity<?> savingJournal(@RequestBody JournalEntry journalEntry,@PathVariable String username) {
        try {
            JournalEntry creating = this.journalService.savingJournalByUsername(journalEntry,username);
            return new ResponseEntity<>(creating, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/{username}")
    public ResponseEntity<?> getById(@PathVariable ObjectId id,@PathVariable String username) {


        Optional<JournalEntry> byId = this.journalService.getJournalByUser(id,username);
        return byId.map(e -> new ResponseEntity<>(e, HttpStatus.FOUND)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping("/{id}/{username}")
    public ResponseEntity<?> updateById(@RequestBody JournalEntry journalEntry, @PathVariable ObjectId id) {
        JournalEntry journalEntry1 = this.journalService.updateById(id, journalEntry);
        if (journalEntry1 != null)

            return new ResponseEntity<>(journalEntry1, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{username}")
    public ResponseEntity<?> getAll(@PathVariable String username) {
        List<JournalEntry> all = this.journalService.getAll(username);
        if(all!=null & !all.isEmpty())

        return new ResponseEntity<>(all, HttpStatus.OK);
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}/{username}")
    public ResponseEntity<?> deletingById(@PathVariable ObjectId id,@PathVariable String username) {
        this.journalService.deleteById(id,username);
        return new ResponseEntity<>("Successfully Deleted ", HttpStatus.NO_CONTENT);
    }

}
