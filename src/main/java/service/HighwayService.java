package service;

import dao.HighwayDAO;
import data.testentity.Highway;
import org.apache.ibatis.session.*;
import sql.Factory;

import java.sql.Connection;
import java.util.ArrayList;

public class HighwayService {
    SqlSessionFactory sqlSessionFactory;
    public HighwayService(){
        sqlSessionFactory = Factory.getSqlSessionFactory();
    }

    public void addHighway(Highway h){
        SqlSession session = sqlSessionFactory.openSession();
        HighwayDAO dao = session.getMapper(HighwayDAO.class);
        dao.insert(h);
        session.commit();
        session.close();
    }

    public Highway getByNo(String highwayCd){
        SqlSession session = sqlSessionFactory.openSession();
        HighwayDAO dao = session.getMapper(HighwayDAO.class);
        Highway h = dao.selectByNo(highwayCd);
        session.close();
        return h;
    }

    public ArrayList<Highway> getAll(){
        SqlSession session = sqlSessionFactory.openSession();
        HighwayDAO dao = session.getMapper(HighwayDAO.class);
        ArrayList<Highway> list = dao.selectAll();
        session.close();
        return list;
    }

    public void delHighway(String highwayCd){
        SqlSession session = sqlSessionFactory.openSession();
        HighwayDAO dao = session.getMapper(HighwayDAO.class);
        dao.delete(highwayCd);
        session.commit();
        session.close();
    }
}
