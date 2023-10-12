package service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import sql.Factory;

public class Service {
    protected SqlSessionFactory sqlSessionFactory;
    protected SqlSession sqlSession;

    public Service() {
        super();
        sqlSessionFactory = Factory.getSqlSessionFactory();
    }
}
