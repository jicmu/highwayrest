package data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class OrderParamDTO {

    private String stdRestCd;
    private String foodNm;
    private int foodNo;
    private int foodCost;
    private int amount;

}
