package dao.review;

import data.dto.RestReviewDTO;
import data.entity.Review;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
@Mapper
public interface ReviewDao {
    @Insert("INSERT INTO review VALUES (#{reviewNo}, #{memberNo}, sysdate, #{content}, #{star}, #{orderNo}, #{img1}, #{img2}, #{img3}, #{restNo})")
    void insert(Review r);

    @Select("SELECT m.nickname, h.svarcd, r.reviewno, r.memberno, TO_CHAR(r.wdate, 'yyyy-MM-dd') AS wdate, r.content, r.star, r.orderno, r.img1, r.img2, r.img3 \n" +
            "FROM highwayrest h, orders o, review r, member m\n" +
            "WHERE h.svarcd = o.restno\n" +
            "AND o.orderno = r.orderno\n" +
            "AND r.restno = #{svarCd} AND r.memberNo=m.memberNo ORDER BY reviewNo DESC")
    ArrayList<RestReviewDTO> selectAllByDate(@Param("svarCd") String svarCd);

    @Select("SELECT m.nickname, h.svarcd, r.reviewno, r.memberno, TO_CHAR(r.wdate, 'yyyy-MM-dd') AS wdate, r.content, r.star, r.orderno, r.img1, r.img2, r.img3\n " +
            "FROM review r, highwayrest h, orders o, member m " +
            "WHERE h.svarCd=o.restNo AND o.orderNo=r.orderNo AND r.memberNo=m.memberNo " +
            "ORDER BY r.star DESC, r.reviewNo DESC")
    ArrayList<RestReviewDTO> selectByHighRate(@Param("svarCd") String svarCd);

    @Select("SELECT m.nickname, h.svarcd, r.reviewno, r.memberno, TO_CHAR(r.wdate, 'yyyy-MM-dd') AS wdate, r.content, r.star, r.orderno, r.img1, r.img2, r.img3\n " +
            "FROM review r, highwayrest h, orders o, member m " +
            "WHERE h.svarCd=o.restNo AND o.orderNo=r.orderNo AND r.memberNo=m.memberNo  " +
            "ORDER BY r.star ASC, r.reviewNo DESC")
    ArrayList<RestReviewDTO> selectByLowRate(@Param("svarCd") String svarCd);

    @Select("SELECT * FROM review WHERE memberNo=#{memberNo} ORDER BY reviewNo DESC")
    ArrayList<Review> selectByMember(@Param("memberNo") int memberNo);

    @Select("SELECT * FROM review WHERE reviewNo=#{reviewNo}")
    Review selectByReviewNo(@Param("reviewNo") int reviewNo);

    @Update("UPDATE review SET content=#{content}, star=#{star}, img1=#{img1}, img2=#{img2}, img3=#{img3} WHERE reviewNo=#{reviewNo}")
    void update(Review r);

    @Delete("DELETE FROM review WHERE reviewNo=#{reviewNo}")
    void delete(@Param("reviewNo") int reviewNo);

    @Select("SELECT seq_review.nextval FROM dual")
    int getSeq();
}