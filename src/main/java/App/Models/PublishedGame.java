package App.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="published_games")
public class PublishedGame {
    @Id
    private int game_id;
    @Column
    private String game_title;

    @Column
    private String game_data;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Column
    private int number_of_plays;
}
