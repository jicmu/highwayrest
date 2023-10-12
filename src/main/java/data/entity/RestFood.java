package data.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RestFood {
    private int no;
    private String name;
    private int accVol;
    private String stdRestCd;
}
