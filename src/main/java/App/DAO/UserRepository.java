package App.DAO;

import App.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    //Query to get the user's salt
    User findUserByUsername(String username);

    //Query to find the user in the user table
    User findUserByUsernameAndPasswordAndSalt(String username, String password, String salt);

}
