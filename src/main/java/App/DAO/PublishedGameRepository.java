package App.DAO;

import App.Models.PublishedGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishedGameRepository extends JpaRepository<PublishedGame, Integer> {
}
