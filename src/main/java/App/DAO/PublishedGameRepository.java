package App.DAO;

import App.Models.PublishedGame;
import App.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublishedGameRepository extends JpaRepository<PublishedGame, Integer> {

    List<PublishedGame> findPublishedGameByUser(User user);
}
