package review;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import sql.Factory;

import java.util.ArrayList;

public class ReviewService {
    private SqlSessionFactory sqlSessionFactory;

    public ReviewService(){
        sqlSessionFactory = Factory.getSqlSessionFactory();
    }

    public void addReview(Review r) {//리뷰 작성
        SqlSession session = sqlSessionFactory.openSession();
        ReviewDao dao = session.getMapper(ReviewDao.class);
        dao.insert(r);
        session.commit();
        session.close();
    }

    public ArrayList<Review> getAll(){//모든 리뷰 조회
        SqlSession session = sqlSessionFactory.openSession();
        ReviewDao dao = session.getMapper(ReviewDao.class);
        ArrayList<Review> list = dao.selectAll();
        session.close();
        return list;
    }

    public ArrayList<Review> getAllByHighRate(){//높은 별점 순으로 조회
        SqlSession session = sqlSessionFactory.openSession();
        ReviewDao dao = session.getMapper(ReviewDao.class);
        ArrayList<Review> list = dao.selectByHighRate();
        session.close();
        return list;
    }

    public ArrayList<Review> getAllByLowRate(){//낮은 별점 순으로 조회
        SqlSession session = sqlSessionFactory.openSession();
        ReviewDao dao = session.getMapper(ReviewDao.class);
        ArrayList<Review> list = dao.selectByLowRate();
        session.close();
        return list;
    }

    public Review getReviewByMember(int memberNo) {//멤버별 리뷰 조회 => 내 후기 조회
        SqlSession session = sqlSessionFactory.openSession();
        ReviewDao dao = session.getMapper(ReviewDao.class);
        Review r = dao.selectByMember(memberNo);
        session.close();
        return r;
    }

    public void editReview(Review r) {//리뷰 수정
        SqlSession session = sqlSessionFactory.openSession();
        ReviewDao dao = session.getMapper(ReviewDao.class);
        dao.update(r);
        session.commit();
        session.close();
    }

    public void delReview(int reviewNo) {//리뷰 삭제
        SqlSession session = sqlSessionFactory.openSession();
        ReviewDao dao = session.getMapper(ReviewDao.class);
        dao.delete(reviewNo);
        session.commit();
        session.close();
    }
}
