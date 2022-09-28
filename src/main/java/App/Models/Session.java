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

    @OneToOne
    @JoinColumn(name="user_id")
    User user;

    @Id
    String session_id;

    Date session_expiry;
}
