package orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@AllArgsConstructor
@Builder
@Setter
public class Order {
    private Integer orderNo;
    private Integer foodNo;
    private Integer restNo;
    private Integer pay;
    private Integer quantity;
    private Integer memberNo;
    private String ordersNo;
    private Integer status;
    private Date oDate;
    private String menu;
}
