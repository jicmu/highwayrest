package data.entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Search {
    private int searchNo;
    private int searchType;
    private int memberNo;
    private Date searchDate;
    private String searchWord;
}
