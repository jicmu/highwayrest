package data.entity;

import lombok.Getter;
import lombok.Setter;
import orders.Order;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderList {
    private String id;
    private List<Order> list;
}
