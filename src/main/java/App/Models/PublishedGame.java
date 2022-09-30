package App.Models;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="published_games")
@Builder
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
