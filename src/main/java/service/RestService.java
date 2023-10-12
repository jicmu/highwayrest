package service;

import dao.RestDAO;
import data.testentity.Rest;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import sql.Factory;

import java.util.ArrayList;

public class RestService {
    SqlSessionFactory sqlSessionFactory;

    public RestService(){
        sqlSessionFactory = Factory.getSqlSessionFactory();
    }

    public void addRest(Rest r){
        SqlSession session = sqlSessionFactory.openSession();
        RestDAO dao = session.getMapper(RestDAO.class);
        dao.insert(r);
        session.commit();
        session.close();
    }

    public Rest getByRest(String restCd){
        SqlSession session = sqlSessionFactory.openSession();
        RestDAO dao = session.getMapper(RestDAO.class);
        Rest r = dao.selectByRest(restCd);
        session.close();
        return r;
    }

    public ArrayList<Rest> getByHighway(String highwayCd){
        SqlSession session = sqlSessionFactory.openSession();
        RestDAO dao = session.getMapper(RestDAO.class);
        ArrayList<Rest> list = dao.selectHighway(highwayCd);
        session.close();
        return list;
    }

    public void delRest(String restCd){
        SqlSession session = sqlSessionFactory.openSession();
        RestDAO dao = session.getMapper(RestDAO.class);
        dao.delete(restCd);
        session.commit();
        session.close();
    }
}
