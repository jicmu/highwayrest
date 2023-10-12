package service.master;

import dao.master.RestFoodDao;
import data.entity.RestFood;
import service.Service;

import java.util.ArrayList;

public class RestFoodService extends Service {
    public void addFood(ArrayList<RestFood> foodList) {
        sqlSession = sqlSessionFactory.openSession();
        RestFoodDao dao = sqlSession.getMapper(RestFoodDao.class);
        for(RestFood food : foodList)
            dao.insert(food);

        sqlSession.commit();
        sqlSession.close();
    }
}
