package com.keschubay.discussions.service;

import com.keschubay.discussions.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;

public interface AppUserService {
    // todo to create IDs

    public AppUser createUser(AppUser appUser);

}
