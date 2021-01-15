package org.nea.springauth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager em;

    @Test
    public void testCreateUser() {
        User user = new User( "prem@yahoo.com", "prem123", "Prem", "Udayan");
        User savedUser = repo.save(user);
        User existUser = em.find(User.class, savedUser.getId() );
        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());

    }

}
