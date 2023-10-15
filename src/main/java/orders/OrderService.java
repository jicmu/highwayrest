package orders;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sql.Factory;
import dao.order.OrderDao;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
        getMenuByFoodNo(orders);

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
        getMenuByFoodNo(orderList);

        sqlSession.close();

        return orderList;
    }

    public int deny(String ordersNo) {
        Order order = Order.builder()
                .ordersNo(ordersNo)
                .status(2)
                .build();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderDao dao = sqlSession.getMapper(OrderDao.class);

        int count = dao.setStatus(order);

        sqlSession.commit();

        sqlSession.close();

        try {
            cancelKakaoPay(ordersNo);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

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

    public int cancel(String ordersNum) {
        Order order = Order.builder()
                .ordersNo(ordersNum)
                .status(3)
                .build();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderDao dao = sqlSession.getMapper(OrderDao.class);

        int count = dao.setStatus(order);

        sqlSession.commit();

        sqlSession.close();

        return count;
    }

    public int done(String ordersNum) {
        Order order = Order.builder()
                .ordersNo(ordersNum)
                .status(4)
                .build();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderDao dao = sqlSession.getMapper(OrderDao.class);

        int count = dao.setStatus(order);

        sqlSession.commit();

        sqlSession.close();

        return count;
    }

    public int finish(String ordersNum) {
        Order order = Order.builder()
                .ordersNo(ordersNum)
                .status(5)
                .build();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderDao dao = sqlSession.getMapper(OrderDao.class);

        int count = dao.setStatus(order);

        sqlSession.commit();

        sqlSession.close();

        return count;
    }

    public List<Order> findByOrdersNo(String ordersNo) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderDao dao = sqlSession.getMapper(OrderDao.class);

        List<Order> orders = dao.findByOrdersNo(ordersNo);

        sqlSession.close();

        return orders;
    }

    public int findTotalPrice(String ordersNo) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderDao dao = sqlSession.getMapper(OrderDao.class);

        int totalPrice = dao.findTotalPrice(ordersNo);

        sqlSession.close();

        return totalPrice;
    }

    public void cancelKakaoPay(String ordersNo) throws IOException, ParseException {
        // kakao 결제 취소
        URL credentialUrl = Thread.currentThread().getContextClassLoader().getResource("../../WEB-INF/credential.properties");

        Properties properties = new Properties();

        properties.load(new FileReader(credentialUrl.getPath()));

        String kakaoKey = properties.getProperty("kakaoKey");

        try {
            URL url = new URL("https://kapi.kakao.com/v1/payment/cancel");

            HttpURLConnection huc = (HttpURLConnection) url.openConnection();

            huc.setRequestMethod("POST");
            huc.setRequestProperty("Authorization", "KakaoAK " + kakaoKey);
            huc.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            huc.setDoInput(true);
            huc.setDoOutput(true);

            // TODO 추후 수정
            String companyId = "1";

            Map<String, String> params = new HashMap<>();

            params.put("cid", "TC0ONETIME");
            params.put("tid", ordersNo);
            params.put("cancel_tax_free_amount", "0");
            params.put("cancel_amount", String.valueOf(findTotalPrice(ordersNo)));

            String param = "";
            for (Map.Entry<String, String> entry : params.entrySet()) {
                param += entry.getKey() + "=" + entry.getValue() + "&";
            }

            huc.getOutputStream().write(param.getBytes("utf-8"));

            BufferedReader cancelBr = new BufferedReader(new InputStreamReader(huc.getInputStream()));

            JSONParser jsonParser = new JSONParser();
            JSONObject parsed = (JSONObject) jsonParser.parse(cancelBr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Order> getMenuByFoodNo(List<Order> orders) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderDao dao = sqlSession.getMapper(OrderDao.class);

        for (Order o : orders) {
            o.setMenu(dao.findMenuByFoodNo(o.getFoodNo()));
        }

        sqlSession.close();

        return orders;
    }

    public String getMenuByFoodNo(int foodNo) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderDao dao = sqlSession.getMapper(OrderDao.class);

        String menu = dao.findMenuByFoodNo(foodNo);

        sqlSession.close();

        return menu;
    }
}
