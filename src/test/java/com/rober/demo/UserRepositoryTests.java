package com.rober.demo;

import com.rober.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)

public class UserRepositoryTests {
        @Autowired
        private TestEntityManager entityManager;
        @Autowired

        @Test
        public void testCreateUser(){
                User user= new User();
                user.setEmail("rcromerod@ufpso.edu.co");
                user.setPassword("123456");
                user.setFirstName("Rober");
                user.setLastName("Rober");



        }
}
