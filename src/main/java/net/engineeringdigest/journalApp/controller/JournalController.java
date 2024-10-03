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

    @PostMapping
    public ResponseEntity<?> savingJournal(@RequestBody JournalEntry journalEntry) {
        try {
            JournalEntry creating = this.journalService.creating(journalEntry);
            return new ResponseEntity<>(creating, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable ObjectId id) {

        Optional<JournalEntry> byId = this.journalService.getById(id);
        return byId.map(e -> new ResponseEntity<>(e, HttpStatus.FOUND)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@RequestBody JournalEntry journalEntry, @PathVariable ObjectId id) {
        JournalEntry journalEntry1 = this.journalService.updateById(id, journalEntry);
        if (journalEntry1 != null)

            return new ResponseEntity<>(journalEntry1, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<JournalEntry> all = this.journalService.getAll();
        if(all!=null & !all.isEmpty())

        return new ResponseEntity<>(all, HttpStatus.OK);
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletingById(@PathVariable ObjectId id) {
        this.journalService.deleteById(id);
        return new ResponseEntity<>("Successfully Deleted ", HttpStatus.NO_CONTENT);
    }

}
