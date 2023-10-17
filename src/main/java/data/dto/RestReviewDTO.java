package data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Date;
@AllArgsConstructor
@Builder
@Getter
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
