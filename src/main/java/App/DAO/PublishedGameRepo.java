package App.DAO;

import App.Models.PublishedGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishedGameRepo extends JpaRepository<PublishedGame, Integer> {
}
