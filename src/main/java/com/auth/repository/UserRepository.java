package com.auth.repository;

import com.auth.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    // Find by email or phone for manual login
    @Query("SELECT u FROM Users u WHERE u.email = :identifier OR u.phoneNumber = :identifier")
    Optional<Users> findByIdentifier(@Param("identifier") String identifier);

    // Find by email (useful for OAuth2 like Google/Facebook)
    Optional<Users> findByEmail(String email);

    // Optional: for social login binding
    Optional<Users> findByFacebookId(String facebookId);
    Optional<Users> findByGoogleId(String googleId);
}

