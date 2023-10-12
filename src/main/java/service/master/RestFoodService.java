package service.master;

import dao.master.FoodDao;
import data.entity.RestFood;
import service.Service;

import java.util.ArrayList;

public class RestFoodService extends Service {
    public void addFood(ArrayList<RestFood> foodList) {
        sqlSession = sqlSessionFactory.openSession();
        FoodDao dao = sqlSession.getMapper(FoodDao.class);
        for(RestFood food : foodList)
            dao.insert(food);

        sqlSession.commit();
        sqlSession.close();
    }
}
