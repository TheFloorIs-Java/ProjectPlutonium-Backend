package App.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="user_scores")
@Builder
@ToString()
public class ScoreCard {

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name="user_id")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int score_id;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name="published_game_id")
    private PublishedGame publishedGame;

    @Column
    private Date date_submitted;
    @Column
    private int score;



}
