package data.entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review {
    private int reviewNo;
    private int memberNo;
    private Date wDate;
    private String content;
    private int star;
    private int orderNo;
    private String img1;
    private String img2;
    private String img3;
}
