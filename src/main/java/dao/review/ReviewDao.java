package dao.review;

import data.dto.RestReviewDTO;
import data.entity.Review;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
@Mapper
public interface ReviewDao {
    @Insert("INSERT INTO review VALUES (#{reviewNo}, #{memberNo}, sysdate, #{content}, #{star}, #{orderNo}, #{img1}, #{img2}, #{img3}, #{restNo})")
    void insert(Review r);//후기 작성

    @Select("SELECT m.nickname, h.svarcd, r.reviewno, r.memberno, TO_CHAR(r.wdate, 'yyyy-MM-dd') AS wdate, r.content, r.star, r.orderno, r.img1, r.img2, r.img3 \n" +
            "FROM highwayrest h, orders o, review r, member m\n" +
            "WHERE h.svarcd = o.restno\n" +
            "AND o.orderno = r.orderno\n" +
            "AND r.restno = #{svarCd} AND r.memberNo=m.memberNo ORDER BY reviewNo DESC")
    ArrayList<RestReviewDTO> selectAllByDate(@Param("svarCd") String svarCd);//전체 후기 최신순 정렬

//    @Select("SELECT * FROM review WHERE restNo=#{restNo} ORDER BY wDate DESC")
//    ArrayList<Review> selectAllByDate(@Param("restNo") String restNo);//전체 후기 최신순 정렬

    @Select("SELECT m.nickname, h.svarcd, r.reviewno, r.memberno, TO_CHAR(r.wdate, 'yyyy-MM-dd') AS wdate, r.content, r.star, r.orderno, r.img1, r.img2, r.img3\n " +
            "FROM review r, highwayrest h, orders o, member m " +
            "WHERE h.svarCd=o.restNo AND o.orderNo=r.orderNo AND r.memberNo=m.memberNo " +
            "ORDER BY r.star DESC, r.reviewNo DESC")
    ArrayList<RestReviewDTO> selectByHighRate(@Param("svarCd") String svarCd);//별점이 높고 최근에 작성한 리뷰순

//    @Select("SELECT * FROM review WHERE restNo=#{restNo} FROM review WHERE restNo=#{restNo}  ORDER BY r.star DESC, r.wDate DESC")
//    ArrayList<Review> selectByHighRate(@Param("restNo") String restNo);//별점이 높고 최근에 작성한 리뷰순

    @Select("SELECT m.nickname, h.svarcd, r.reviewno, r.memberno, TO_CHAR(r.wdate, 'yyyy-MM-dd') AS wdate, r.content, r.star, r.orderno, r.img1, r.img2, r.img3\n " +
            "FROM review r, highwayrest h, orders o, member m " +
            "WHERE h.svarCd=o.restNo AND o.orderNo=r.orderNo AND r.memberNo=m.memberNo  " +
            "ORDER BY r.star ASC, r.reviewNo DESC")
    ArrayList<RestReviewDTO> selectByLowRate(@Param("svarCd") String svarCd);//별점 낮고 최근에 작성한 리뷰순

//    @Select("SELECT * FROM review WHERE restNo=#{restNo} ORDER BY r.star ASC, r.wDate DESC")
//    ArrayList<Review> selectByLowRate(@Param("restNo") String restNo);//별점 낮고 최근에 작성한 리뷰순

    @Select("SELECT * FROM review WHERE memberNo=#{memberNo}")
    ArrayList<Review> selectByMember(@Param("memberNo") int memberNo);//멤버별 후기

    @Select("SELECT * FROM review WHERE reviewNo=#{reviewNo}")
    Review selectByReviewNo(@Param("reviewNo") int reviewNo);

    @Update("UPDATE review SET content=#{content}, star=#{star}, img1=#{img1}, img2=#{img2}, img3=#{img3} WHERE reviewNo=#{reviewNo}")
    void update(Review r);//후기 수정

    @Delete("DELETE FROM review WHERE reviewNo=#{reviewNo}")
    void delete(@Param("reviewNo") int reviewNo);//후기 삭제

    @Select("SELECT seq_review.nextval FROM dual")
    int getSeq();//시퀀스
}
