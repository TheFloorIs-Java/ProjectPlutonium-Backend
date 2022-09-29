package App.Models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="sessions")
@ToString
@Builder
public class Session {


    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    User user;

    @Id
    @Column
    String session_id;

    @Column//changed
    Date session_expiry;
}
