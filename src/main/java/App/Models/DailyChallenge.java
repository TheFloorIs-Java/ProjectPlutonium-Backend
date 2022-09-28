package App.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="daily_challenge")
public class DailyChallenge {

    @Id
    Date challenge_date;

    @ManyToOne
    @JoinColumn(name="published_game_id")
    PublishedGame published_game;
}
