package service.master;

import dao.master.RestMasterDao;
import data.dto.RestMasterStatusDTO;
import data.entity.RestMaster;
import service.Service;

import java.util.ArrayList;

public class RestMasterService extends Service {
    public int addMaster(RestMaster master) {
        int status = 0;
        sqlSession = sqlSessionFactory.openSession();
        RestMasterDao dao = sqlSession.getMapper(RestMasterDao.class);
        String id = dao.selectMasterNo(master.getMasterNo());

        if(id == null && master != null) {
            dao.insert(master);
            status = 1;
        }

        sqlSession.commit();
        sqlSession.close();

        return status;
    }

    public int getNo() {
        sqlSession = sqlSessionFactory.openSession();
        RestMasterDao dao = sqlSession.getMapper(RestMasterDao.class);
        int no = dao.selectNo();

        sqlSession.close();

        return no;
    }

    public ArrayList<RestMasterStatusDTO> getAllMaster() {
        sqlSession = sqlSessionFactory.openSession();
        RestMasterDao dao = sqlSession.getMapper(RestMasterDao.class);
        ArrayList<RestMasterStatusDTO> list = dao.selectStatus();

        sqlSession.close();

        return list;
    }

}
