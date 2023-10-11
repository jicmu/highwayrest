package orders;

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

    public List<Order> getMyOrders(Order order) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderDao dao = sqlSession.getMapper(OrderDao.class);

        List<Order> orders = dao.findMyOrders(order);

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

}
