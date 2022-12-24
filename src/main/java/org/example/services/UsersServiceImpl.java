package org.example.services;

import lombok.AllArgsConstructor;
import org.example.exception.EntityNotFoundException;
import org.example.models.User;
import org.example.repositories.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UsersServiceImpl {
    private final UsersRepository usersRepository;
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return usersRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findOne(int id) throws EntityNotFoundException {
        return usersRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(User user) {
        usersRepository.save(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        updatedUser.setId(id);
        usersRepository.save(updatedUser);
    }

    @Transactional
    public void delete(int id) {
        usersRepository.deleteById(id);
    }
}
