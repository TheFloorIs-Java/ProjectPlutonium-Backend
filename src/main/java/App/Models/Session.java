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
@Entity
@Table(name="sessions")
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
