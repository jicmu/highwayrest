package orders;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderDao {

    @Insert("INSERT INTO orders VALUES(seq_order.NEXTVAL, #{menu}, #{restNo}, #{pay}, #{memberNo}, #{ordersNo}, 0, sysdate)")
    int insert(Order order);

    @Select("SELECT seq_orders.NEXTVAL FROM dual")
    int getSeqOrders();

    @Select("SELECT * FROM order WHERE member = #{member}")
    List<Order> findByMember(@Param("member") int member);

    @Select("SELECT * FROM order WHERE rest LIKE '%#{rest}%'")
    List<Order> findByRest(@Param("rest") String rest);

    @Select("SELECT * FROM order WHERE member = #{member} AND ordersNo = #{ordersNo}")
    List<Order> findMyOrders(Order order);

    @Update("UPDATE order SET status = #{status} WHERE orderNo = #{orderNo}")
    int setStatus(Order order);

    @Update("UPDATE pay SET pay = #{pay} WHERE orderNo = #{orderNo}")
    int setPay(Order order);

}
