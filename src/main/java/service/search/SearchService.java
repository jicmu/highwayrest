package service.search;

import dao.search.SearchDao;
import data.entity.Search;
import org.apache.ibatis.session.SqlSession;
import service.Service;

import java.sql.Date;
import java.util.ArrayList;

public class SearchService extends Service {

    public void addSearch(Search s){
        SqlSession session = sqlSessionFactory.openSession();
        SearchDao dao = session.getMapper(SearchDao.class);

        dao.insert(s);

        session.commit();
        session.close();
    }

    public ArrayList<Search> getByMember(int memberNo){
        SqlSession session = sqlSessionFactory.openSession();
        SearchDao dao = session.getMapper(SearchDao.class);

        ArrayList<Search> list = dao.selectByMember(memberNo);

        session.close();
        return list;
    }

    public ArrayList<Search> getByType(int searchType){
        SqlSession session = sqlSessionFactory.openSession();
        SearchDao dao = session.getMapper(SearchDao.class);

        ArrayList<Search> list = dao.selectByType(searchType);

        session.close();
        return list;
    }

    public ArrayList<Search> getByDate(Date searchDate){
        SqlSession session = sqlSessionFactory.openSession();
        SearchDao dao = session.getMapper(SearchDao.class);

        ArrayList<Search> list = dao.selectByDate(searchDate);

        session.close();
        return list;
    }

    public void delSearch(int searchNo){
        SqlSession session = sqlSessionFactory.openSession();
        SearchDao dao = session.getMapper(SearchDao.class);

        dao.delete(searchNo);

        session.commit();
        session.close();
    }
}
