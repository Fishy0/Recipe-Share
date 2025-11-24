package com.recipeshare.enterprise;

import com.recipeshare.enterprise.dao.UserDAO;
import com.recipeshare.enterprise.dto.UserDTO;
import com.recipeshare.enterprise.entity.Users;
import com.recipeshare.enterprise.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserDAOTest {

    @InjectMocks
    private UserDAO userDAO;

    @Mock
    private UserRepository repo;

    private Users user;
    private UserDTO userDTO;

    @BeforeEach
    void setup() {
        user = new Users();
        user.setUserId(1);
        user.setUserName("Bob");
        user.setUserEmail("Bob@gmail.com");
        user.setUserPassword("password");
        user.setNumberOfRecipes(3);
        user.setProfileDescription("A profile description");

        userDTO = new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserPassword(),
                user.getNumberOfRecipes(),
                user.getProfileDescription()
        );
    }

    @Test
    void GivenUsersExist_WhenGetAllUsers_ThenReturnUsers() {
        // Given
        given(repo.findAll()).willReturn(List.of(user));

        // When
        List<UserDTO> result = userDAO.getAllUsers();

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getUserName()).isEqualTo("Bob");
    }

    @Test
    void GivenUserId_WhenGetUserById_ThenReturnUser() {
        // Given
        given(repo.findById(1)).willReturn(Optional.of(user));

        // When
        UserDTO result = userDAO.getUserById(1);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getUserEmail()).isEqualTo("Bob@gmail.com");
    }

    @Test
    void GivenUser_WhenSaveUser_ThenSave() {
        // Given
        given(repo.save(any(Users.class))).willReturn(user);

        // When
        userDAO.saveUser(userDTO);

        // Then
        then(repo).should().save(any(Users.class));
    }

    @Test
    void GivenUserId_WhenDeleteById_ThenDeleteById() {
        // When
        userDAO.deleteById(1);

        // Then
        then(repo).should().deleteById(1);
    }

    @Test
    void GivenValidUserPass_WhenValidateUser_ThenReturnTrue() {
        // Given
        given(repo.findByUserNameAndUserPassword("Bob", "password"))
                .willReturn(Optional.of(user));

        // When
        boolean result = userDAO.validateUser("Bob", "password");

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void GivenInvalidUserPass_WhenValidateUser_ThenReturnFalse() {
        // Given
        given(repo.findByUserNameAndUserPassword("Bob", "wrong"))
                .willReturn(Optional.empty());

        // When
        boolean result = userDAO.validateUser("Bob", "wrong");

        // Then
        assertThat(result).isFalse();
    }

    @Test
    void GivenUserName_WhenFindByUserName_ThenReturnUser() {
        // Given
        given(repo.findByUserName("Bob")).willReturn(Optional.of(user));

        // When
        Optional<Users> result = userDAO.findByUserName("Bob");

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getUserName()).isEqualTo("Bob");
    }
}
