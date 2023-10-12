package orders;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import sql.Factory;

import java.util.List;

public class OrderService {

    SqlSessionFactory sqlSessionFactory;

    public OrderService() {
        sqlSessionFactory = Factory.getSqlSessionFactory();
    }

    public int order(Order order) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderDao dao = sqlSession.getMapper(OrderDao.class);

        int inserted = dao.insert(order);

        sqlSession.commit();

        sqlSession.close();

        return inserted;
    }

    public List<Order> getMyOrders(int memberId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderDao dao = sqlSession.getMapper(OrderDao.class);

        List<Order> orders = dao.findByMember(memberId);

        sqlSession.close();

        return orders;
    }

    public int getNextOrdersNo() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderDao dao = sqlSession.getMapper(OrderDao.class);

        int ordersNo = dao.getSeqOrders();

        sqlSession.close();

        return ordersNo;
    }

    public List<Order> findByRestNo(@Param("restNo") int restNo) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderDao dao = sqlSession.getMapper(OrderDao.class);

        List<Order> orderList = dao.findByRestNo(restNo);

        sqlSession.close();

        return orderList;
    }

    public int deny(String ordersNum) {
        Order order = Order.builder()
                .ordersNo(ordersNum)
                .status(2)
                .build();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderDao dao = sqlSession.getMapper(OrderDao.class);

        int count = dao.setStatus(order);

        sqlSession.commit();

        sqlSession.close();

        return count;
    }

    public int accept(String ordersNum) {
        Order order = Order.builder()
                .ordersNo(ordersNum)
                .status(1)
                .build();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderDao dao = sqlSession.getMapper(OrderDao.class);

        int count = dao.setStatus(order);

        sqlSession.commit();

        sqlSession.close();

        return count;
    }

}
