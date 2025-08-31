package com.even.gestion.services;

import com.even.gestion.models.AppUser;
import com.even.gestion.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository repo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Password encoder

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = repo.findByEmail(email);

        if (appUser != null) {
            // Ensure the role is prefixed correctly
            String role = "ROLE_" + appUser.getRole();

            // Debugging: Print the user's email and role
            System.out.println("User: " + appUser.getEmail() + " - Role: " + role);

            var springUser = User.withUsername(appUser.getEmail())
                    .password(appUser.getPassword()) // Ensure password is correctly stored
                    .authorities(new SimpleGrantedAuthority(role)) // Assign authority
                    .build();
            return springUser;
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }




    // Save user with encoded password
    public AppUser saveUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword())); // Encoding password
        return repo.save(appUser);
    }

    // Save or update user, ensuring password is encoded
    public AppUser saveOrUpdateUser(AppUser appUser) {
        if (appUser.getPassword() != null) {
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword())); // Ensure password is encoded
        }
        return repo.save(appUser);
    }

    public List<AppUser> getAllUsers() {
        return repo.findAll();
    }

    public void deleteUser(int id) {
        repo.deleteById(id);
    }

    public Optional<AppUser> getUserById(int id) {
        return repo.findById(id);
    }
}
