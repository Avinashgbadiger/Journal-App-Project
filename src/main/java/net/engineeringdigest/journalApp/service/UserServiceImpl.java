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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users creating(Users users) {
        try {
            return this.userRepository.save(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Users> getById(ObjectId id) {
        Optional<Optional<Users>> byId = Optional.of(this.userRepository.findById(id));
        return byId.get();

    }

    @Override
    public Users updateById(Users users, String username) {
        Users byUsername = this.userRepository.findByUsername(username);
        if (byUsername != null) {
            byUsername.setUsername(users.getUsername());
            byUsername.setPassword(users.getPassword());
            try {
                return this.userRepository.save(byUsername);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return byUsername;
    }

    @Override
    public List<Users> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public void deleteById(ObjectId id) {
        this.userRepository.deleteById(id);

    }
}
