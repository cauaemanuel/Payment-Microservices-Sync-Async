package com.user_service.persistence;


import com.user_service.infrastructure.persistence.SpringDataUserRepository;
import com.user_service.infrastructure.persistence.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private SpringDataUserRepository springDataUserRepository;

    @Nested
    class FindByEmailTest {

        @Test
        void testFindByEmail() {
            UserEntity user = new UserEntity();
            user.setNome("Test User");
            user.setSenha("password123");
            user.setEmail("test@gmail.com");

            springDataUserRepository.save(user);

            var foundUser = springDataUserRepository.findByEmail("test@gmail.com");

            Assertions.assertThat(foundUser)
                    .isPresent()
                    .hasValueSatisfying(u -> {
                        Assertions.assertThat(u.getNome()).isEqualTo("Test User");
                        Assertions.assertThat(u.getEmail()).isEqualTo("test@gmail.com");
                        Assertions.assertThat(u.getSenha()).isEqualTo("password123");
                    });
        }

        @Test
        void foundExceptionWhenEmailNotFound() {
            var foundUser = springDataUserRepository.findByEmail("test2@gmail.com");

            Assertions.assertThat(foundUser).isNotPresent();

            Assertions.assertThatThrownBy(() -> {
                throw new RuntimeException("Email not found");
            }).isInstanceOf(RuntimeException.class).hasMessage("Email not found");
        }
    }


}
