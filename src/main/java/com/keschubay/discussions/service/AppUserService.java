package com.keschubay.discussions.service;

import com.keschubay.discussions.model.AppUser;

import java.util.Optional;

public interface AppUserService {
    public AppUser createUser(AppUser appUser);
    public void deleteUser(AppUser appUser);
    public Optional<AppUser> getUserById(Long id);
}
