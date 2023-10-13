package dao.order;

import orders.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderDao {

    @Insert("INSERT INTO orders VALUES(seq_order.NEXTVAL, #{foodNo}, #{restNo}, #{pay}, #{quantity}, #{memberNo}, #{ordersNo}, 0, sysdate)")
    int insert(Order order);

    @Select("SELECT seq_orders.NEXTVAL FROM dual")
    int getSeqOrders();

    @Select("SELECT orderno, foodno, restno, pay, quantity, memberno, ordersno, status, odate, name FROM orders o INNER JOIN restfood r ON o.foodno = r.no WHERE memberNo = 1 ORDER BY odate DESC")
    List<Order> findByMember(@Param("memberNo") int memberNo);

    @Select("SELECT * FROM orders WHERE rest LIKE '%#{rest}%' ORDER BY odate DESC")
    List<Order> findByRest(@Param("rest") String rest);

    @Select("SELECT * FROM orders WHERE memberno = #{memberno} AND ordersNo = #{ordersNo}")
    List<Order> findMyOrders(Order order);

    @Update("UPDATE orders SET status = #{status} WHERE ordersNo = #{ordersNo}")
    int setStatus(Order order);

    @Select("SELECT orderno, foodno, restno, pay, quantity, memberno, ordersno, status, odate, name FROM orders o INNER JOIN restfood r ON o.foodno = r.no WHERE restno = #{restNo} ORDER BY odate DESC")
    List<Order> findByRestNo(@Param("restNo") int restNo);

    @Select("SELECT * FROM orders WHERE ordersNo = #{ordersNo}")
    List<Order> findByOrdersNo(@Param("orderNo") String ordersNo);

    @Select("SELECT SUM(pay) FROM orders WHERE ordersNo = #{ordersNo} GROUP BY ordersNo")
    int findTotalPrice(@Param("ordersNo") String ordersNo);

    @Select("SELECT name AS menu FROM restFood WHERE no = #{foodNo}")
    String findMenuByFoodNo(@Param("foodNo") int foodNo);
}
