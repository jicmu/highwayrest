package dao.review;

import data.entity.Review;
import data.testdto.RestReviewDTO;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
@Mapper
public interface ReviewDao {
    @Insert("INSERT INTO review VALUES (#{reviewNo}, #{memberNo}, sysdate, #{content}, #{star}, #{orderNo}, #{img1}, #{img2}, #{img3})")
    void insert(Review r);//후기 작성

    @Select("SELECT r.*, h.svarCd FROM review r, highwayrest h, orders o WHERE h.svarcd=o.restno AND o.orderno=r.orderno ORDER BY r.wDate DESC")
    ArrayList<RestReviewDTO> selectAllByDate(@Param("svarCd") String svarCd);//전체 후기 최신순 정렬

    @Select("SELECT r.*, h.svarCd FROM review r, highwayrest h, orders o WHERE h.svarcd=o.restno AND o.orderno=r.orderno ORDER BY r.star DESC, r.wDate DESC")
    ArrayList<RestReviewDTO> selectByHighRate(@Param("svarCd") String svarCd);//별점이 높고 최근에 작성한 리뷰순

    @Select("SELECT r.*, h.svarCd FROM review r, highwayrest h, orders o WHERE h.svarcd=o.restno AND o.orderno=r.orderno ORDER BY r.star ASC, r.wDate DESC")
    ArrayList<RestReviewDTO> selectByLowRate(@Param("svarCd") String svarCd);//별점 낮고 최근에 작성한 리뷰순

    @Select("SELECT * FROM review WHERE memberNo=#{memberNo}")
    ArrayList<Review> selectByMember(@Param("memberNo") int memberNo);//멤버별 후기

    @Select("SELECT * FROM review WHERE reviewNo=#{reviewNo}")
    Review selectByReviewNo(@Param("reviewNo") int reviewNo);

    @Update("UPDATE review SET content=#{content}, star=#{star} WHERE reviewNo=#{reviewNo}")
    void update(Review r);//후기 수정

    @Delete("DELETE FROM review WHERE reviewNo=#{reviewNo}")
    void delete(@Param("reviewNo") int reviewNo);//후기 삭제

    @Select("SELECT seq_review.nextval FROM dual")
    int getSeq();
}
