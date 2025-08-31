package com.even.gestion.repositories;

import com.even.gestion.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    public AppUser findByEmail(String email);
    public Optional<AppUser> findById(int id);
    public void deleteById(int id);
    List<AppUser> findAll();

}
