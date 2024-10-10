package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.model.JournalEntry;
import net.engineeringdigest.journalApp.model.Users;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Users creating(Users users);

    Optional<Users> getById(ObjectId id);

    Users updateById(Users users,String username);

    List<Users>  getAll();

    void deleteById(ObjectId id);
}
