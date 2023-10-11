package data.entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class restMaster {
    private int masterNo;
    private String id;
    private String password;
    private Date logout;
    private Date lastIn;
    private String tel;
    private int status;
}
