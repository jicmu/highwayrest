package service.master;

import dao.master.HighwayDao;
import data.entity.Highway;
import service.Service;

public class HighwayService extends Service {
    public void addHighway(Highway item) {
        sqlSession = sqlSessionFactory.openSession();
        HighwayDao dao = sqlSession.getMapper(HighwayDao.class);
        String routeCd = null;
        routeCd = dao.select(item.getRouteCd());
        if(routeCd == null)
            dao.insert(item);
        else
            System.out.println("이미 있는 도로입니다.");

        sqlSession.commit();
        sqlSession.close();
    }
}
