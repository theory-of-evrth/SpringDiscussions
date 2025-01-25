package com.keschubay.discussions.service;

import com.keschubay.discussions.model.AppUser;
import com.keschubay.discussions.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl  implements AppUserService
{

    @Qualifier("appUserRepositoryImpl")
    @Autowired
    public AppUserRepository repository;


    @Override
    public AppUser createUser(AppUser appUser) {
        return repository.save(appUser);
    }

    @Override
    public void deleteUser(AppUser appUser) {
        repository.deleteById(appUser.getId());
    }

    @Override
    public Optional<AppUser> getUserById(Long id) {
        return repository.findById(id);
    }
}
