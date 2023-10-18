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

    @Select("SELECT orderno, foodno, restno, pay, quantity, memberno, ordersno, status, odate, name FROM orders o INNER JOIN restfood r ON o.foodno = r.no WHERE memberNo = #{memberNo} ORDER BY odate DESC")
    List<Order> findByMember(@Param("memberNo") int memberNo);

    @Select("SELECT orderno, foodno, restno, pay, quantity, memberno, ordersno, status, odate, name FROM (SELECT ROWNUM AS rn, orderno, foodno, restno, pay, quantity, memberno, ordersno, status, odate, name FROM (SELECT orderno, foodno, restno, pay, quantity, memberno, ordersno, status, odate, name  FROM orders o INNER JOIN restfood r ON o.foodno = r.no  WHERE memberNo = #{memberNo} ORDER BY odate DESC )) WHERE  ((#{page} - 1) * #{amount}) + 1 <= rn AND rn <= (#{page} * #{amount})")
    List<Order> findByMemberWithPaging(@Param("memberNo") int memberNo, @Param("page") int page, @Param("amount") int amount);

    @Select("SELECT * FROM orders WHERE rest LIKE '%#{rest}%' ORDER BY odate DESC")
    List<Order> findByRest(@Param("rest") String rest);

    @Select("SELECT * FROM orders WHERE memberno = #{memberno} AND ordersNo = #{ordersNo}")
    List<Order> findMyOrders(Order order);

    @Update("UPDATE orders SET status = #{status} WHERE ordersNo = #{ordersNo}")
    int setStatus(Order order);

    @Update("UPDATE orders SET status = #{status} WHERE orderNo = #{orderNo}")
    int setStatusByOrderNo(Order order);

    @Select("SELECT orderno, foodno, restno, pay, quantity, memberno, ordersno, status, odate, name FROM orders o INNER JOIN restfood r ON o.foodno = r.no WHERE restno = #{restNo} ORDER BY odate DESC")
    List<Order> findByRestNo(@Param("restNo") String restNo);

    @Select("SELECT * FROM orders WHERE ordersNo = #{ordersNo}")
    List<Order> findByOrdersNo(@Param("orderNo") String ordersNo);

    @Select("SELECT SUM(pay) FROM orders WHERE ordersNo = #{ordersNo} GROUP BY ordersNo")
    Integer findTotalPrice(@Param("ordersNo") String ordersNo);

    @Select("SELECT name AS menu FROM restFood WHERE no = #{foodNo}")
    String findMenuByFoodNo(@Param("foodNo") int foodNo);

    @Select("SELECT orderno, foodno, restno, pay, quantity, memberno, ordersno, status, odate, name FROM orders o INNER JOIN restfood r ON o.foodno = r.no WHERE orderNo = #{orderNo}")
    Order findByOrderNo(@Param("orderNo") String orderNo);

    @Select("SELECT orderno, foodno, restno, pay, quantity, memberno, ordersno, status, odate, name FROM (SELECT ROWNUM AS rn, orderno, foodno, restno, pay, quantity, memberno, ordersno, status, odate, name FROM (SELECT orderno, foodno, restno, pay, quantity, memberno, ordersno, status, odate, name  FROM orders o INNER JOIN restfood r ON o.foodno = r.no  WHERE restno = #{restno} ORDER BY odate DESC )) WHERE  ((#{page} - 1) * #{amount}) + 1 <= rn AND rn <= (#{page} * #{amount})")
    List<Order> findByRestNoWithPaging(@Param("restno") int restno, @Param("page") int page, @Param("amount") int amount);
}
