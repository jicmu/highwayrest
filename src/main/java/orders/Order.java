package orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
@Builder
public class Order {

    private Integer orderNo;
    private String menu;
    private Integer restNo;
    private Integer pay;
    private Integer memberNo;
    private Integer ordersNo;
    private Integer status;
    private Date oDate;

}
