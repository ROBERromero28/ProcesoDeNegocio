package com.rober.demo;

import com.rober.demo.entity.User;
import com.rober.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)

public class UserRepositoryTests {

        @Autowired
        private TestEntityManager entityManager;
        @Autowired
        private UserRepository repository;
        @Autowired
        private PasswordEncoder passwordEncoder;
        @Test
        public void testCreateUser(){
                User user= new User();
                user.setEmail("drarrietam@ufpso.edu.co");
                user.setPassword(passwordEncoder.encode("123456"));
                user.setFirstName("Darling");
                user.setLastName("Romero");

                User savedUser = repository.save(user);
                User exitstUser = entityManager.find(User.class, savedUser.getId());

                assertEquals(user.getEmail(), exitstUser.getEmail());


        }
}
