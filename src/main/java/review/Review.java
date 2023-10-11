package review;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Review {
    private int reviewNo;
    private int memberNo;
    private Date wDate;
    private String content;
    private int star;
    private int orderNo;
    //TODO File 추가?
    public Review(int reviewNo, int star, Date wDate, String content) {
        this.reviewNo = reviewNo;
        this.star = star;
        this.wDate = wDate;
        this.content = content;
    }
}


