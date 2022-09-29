package App.Models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_info")
@Builder
@ToString
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int user_id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String profile_pic_url;
    @Column
    private int permission_level;
    @Column
    private String salt;

}