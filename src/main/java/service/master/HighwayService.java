package service.master;

import dao.master.HighwayDao;
import data.dto.HighwayRestDTO;
import data.entity.Highway;
import service.Service;

import java.util.ArrayList;

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

    public void rest(String no) {
        sqlSession = sqlSessionFactory.openSession();
        HighwayDao dao = sqlSession.getMapper(HighwayDao.class);
        ArrayList<HighwayRestDTO> list = dao.joinCheck(no);

        sqlSession.close();

        for (HighwayRestDTO item : list) {
            System.out.println("item: " + item);
        }
    }
  
    public Highway getByCd(String routeCd){
        sqlSession = sqlSessionFactory.openSession();
        HighwayDao dao = sqlSession.getMapper(HighwayDao.class);
        Highway highway = dao.selectByCd(routeCd);

        sqlSession.close();

        return highway;
    }

    public ArrayList<Highway> getByNm(String routeNm){
        sqlSession = sqlSessionFactory.openSession();
        HighwayDao dao = sqlSession.getMapper(HighwayDao.class);
        ArrayList<Highway> list = dao.selectByNm("%" + routeNm + "%");

        sqlSession.close();

        return list;
    }

    public ArrayList<Highway> getAll(){
        sqlSession = sqlSessionFactory.openSession();
        HighwayDao dao = sqlSession.getMapper(HighwayDao.class);
        ArrayList<Highway> list = dao.selectAll();

        sqlSession.close();

        return list;
    }
}
