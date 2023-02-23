package ftclub.cabinet.user;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "user")
@Getter
public class UserEntity {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "intra_id")
    private String intraId;

    @Column(name = "email")
    private String email;

    @Column(name = "first_login")
    private Date firstLogin;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "blackhole_date")
    private Date blackholeDate;
}
