package com.keschubay.discussions.repository;

import com.keschubay.discussions.model.AppUser;
import com.keschubay.discussions.repository.AppUserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AppUserRepositoryImpl extends SimpleJpaRepository<AppUser, Long> implements AppUserRepository {

    private final EntityManager entityManager;

    // Constructor injection for EntityManager
    public AppUserRepositoryImpl(EntityManager entityManager) {
        super(AppUser.class, entityManager); // Pass the entity class and entity manager to SimpleJpaRepository
        this.entityManager = entityManager;
    }

    @Override
    public Optional<AppUser> findByUsername(String username) {
        String query = "SELECT u FROM AppUser u WHERE u.username = :username";
        return entityManager.createQuery(query, AppUser.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }
}