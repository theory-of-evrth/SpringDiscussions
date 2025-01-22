package com.keschubay.discussions.service;

import com.keschubay.discussions.model.AppUser;
import com.keschubay.discussions.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl  implements AppUserService
{

    @Autowired
    public AppUserRepository repository;


    @Override
    public AppUser createUser(AppUser appUser) {
        return repository.save(appUser);
    }
}
