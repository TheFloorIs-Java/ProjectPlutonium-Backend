package App.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_info")
public class User {

    @Id
    int user_id;
    @Column
    String username;
    @Column
    String password;
    @Column
    String profile_pic_url;
    @Column
    int permission_level;
    @Column
    String salt;

}