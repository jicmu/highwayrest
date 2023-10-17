package service.review;

import dao.review.ReviewDao;
import data.dto.RestReviewDTO;
import data.entity.Review;
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

    public ArrayList<RestReviewDTO> getAll(String svarCd){//모든 리뷰 조회
        SqlSession session = sqlSessionFactory.openSession();
        ReviewDao dao = session.getMapper(ReviewDao.class);
        ArrayList<RestReviewDTO> list = dao.selectAllByDate(svarCd);
        session.close();
        return list;
    }

    public ArrayList<RestReviewDTO> getAllByHighRate(String svarCd){//높은 별점 순으로 조회
        SqlSession session = sqlSessionFactory.openSession();
        ReviewDao dao = session.getMapper(ReviewDao.class);
        ArrayList<RestReviewDTO> list = dao.selectByHighRate(svarCd);
        session.close();
        return list;
    }

    public ArrayList<RestReviewDTO> getAllByLowRate(String svarCd){//낮은 별점 순으로 조회
        SqlSession session = sqlSessionFactory.openSession();
        ReviewDao dao = session.getMapper(ReviewDao.class);
        ArrayList<RestReviewDTO> list = dao.selectByLowRate(svarCd);
        session.close();
        return list;
    }

    public ArrayList<Review> getReviewByMember(int memberNo) {//멤버별 리뷰 조회 => 내 후기 조회
        SqlSession session = sqlSessionFactory.openSession();
        ReviewDao dao = session.getMapper(ReviewDao.class);
        ArrayList<Review> list = dao.selectByMember(memberNo);
        session.close();
        return list;
    }

    public Review getReviewByReviewNo(int reviewNo) {//멤버별 리뷰 조회 => 내 후기 조회
        SqlSession session = sqlSessionFactory.openSession();
        ReviewDao dao = session.getMapper(ReviewDao.class);
        Review r = dao.selectByReviewNo(reviewNo);
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

    public int getSeq(){//시퀀스
        SqlSession session = sqlSessionFactory.openSession();
        ReviewDao dao = session.getMapper(ReviewDao.class);
        int no = dao.getSeq();
        session.close();

        return no;
    }

    public void editImg(Review r){//사진 수정
        SqlSession session = sqlSessionFactory.openSession();
        ReviewDao dao = session.getMapper(ReviewDao.class);
        dao.updateImg(r);
        session.commit();
        session.close();
    }
}
