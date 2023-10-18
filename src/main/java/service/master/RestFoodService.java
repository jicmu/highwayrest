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

    public ArrayList<RestFood> getBySCd(String stdRestCd){
        sqlSession = sqlSessionFactory.openSession();
        RestFoodDao dao = sqlSession.getMapper(RestFoodDao.class);
        ArrayList<RestFood> list = dao.selectBySCd(stdRestCd);

        sqlSession.close();

        return list;
    }

    public ArrayList<RestFood> getByName(String name, String stdRestCd){
        sqlSession = sqlSessionFactory.openSession();
        RestFoodDao dao = sqlSession.getMapper(RestFoodDao.class);
        ArrayList<RestFood> list = dao.selectByName("%" + name + "%", stdRestCd);

        sqlSession.close();

        return list;
    }
}
