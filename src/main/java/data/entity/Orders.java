package data.entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Orders {
    private int orderNo;
    private String menu;
    private int restNo;
    private int pay;
    private int memberNo;
    private int ordersNo;
    private int status;
    private Date oDate;
}
