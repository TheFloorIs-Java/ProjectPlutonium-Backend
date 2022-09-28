package App.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    int id;
    @Column
    String username;
    @Column
    String password;
    @Column
    String profile_pic_url;
    @Column
    int permissionLevel;
    @Column
    String salt;
}