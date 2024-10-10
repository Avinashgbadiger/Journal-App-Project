package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.model.JournalEntry;
import net.engineeringdigest.journalApp.model.Users;
import net.engineeringdigest.journalApp.service.JournalService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-users")
public class UserController {

    @Autowired
    @Qualifier(value = "userServiceImpl")
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> savingJournal(@RequestBody Users users) {
        try {
            Users creating = this.userService.creating(users);
            return new ResponseEntity<>(creating, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable ObjectId id) {

        Optional<Users> byId = this.userService.getById(id);
        return byId.map(e -> new ResponseEntity<>(e, HttpStatus.FOUND)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateById(@RequestBody Users users, @PathVariable String username) {
        Users users1 = this.userService.updateById(users,username);
        if (users1 != null)

            return new ResponseEntity<>(users1, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Users> all = this.userService.getAll();
        if(all!=null & !all.isEmpty())

        return new ResponseEntity<>(all, HttpStatus.OK);
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletingById(@PathVariable ObjectId id) {
        this.userService.deleteById(id);
        return new ResponseEntity<>("Successfully Deleted ", HttpStatus.NO_CONTENT);
    }

}
