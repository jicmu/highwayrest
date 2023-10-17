package data.entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RestMaster {
    private int masterNo;
    private String id;
    private Date logOut;
    private Date lastIn;
    private String tel;
    private int status;
}
