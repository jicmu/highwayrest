package data.dto;

import lombok.*;

import java.sql.Date;
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class RestReviewDTO {
    private String svarCd;
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
