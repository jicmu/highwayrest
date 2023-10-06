package data.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Food {
    private int foodNo;
    private String menu;
    private int accVol;
    private int restNo;
}
