package data.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
    private int memberNo;
    private String id;
    private String password;
    private String name;
    private String nickname;
    private String email;
    private String tel;
}
