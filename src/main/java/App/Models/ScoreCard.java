package App.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name="user_scores")
public class ScoreCard {

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Id
    @Column
    private int score_id;

    @ManyToOne
    @JoinColumn(name="published_game_id")
    private PublishedGame published_game;

    @Column
    private Date date_submitted;
    @Column
    private int score;

}
