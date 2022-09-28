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
public class ScoreCard {

    @ManyToOne
    @JoinColumn(name="id")
    @Column
    private User user;
    @Id
    @Column
    private int scoreId;
    @Column
    private PublishedGame publishedGame;
    @Column
    private Date date;
    @Column
    private int score;

}
