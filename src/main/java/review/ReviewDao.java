package review;

import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
@Mapper
public interface ReviewDao {
    @Insert("INSERT INTO review VALUES (seq_review.nextval, #{memberNo}, sysdate, #{content}, #{star}, #{orderNo})")
    void insert(Review r);//후기 작성

    @Select("SELECT * FROM review")
    ArrayList<Review> selectAll();//전체 후기 목록

    @Select("SELECT * FROM review ORDER BY sysdate DESC")
    ArrayList<Review> selectByDate();//최신순

    @Select("SELECT * FROM review ORDER BY star DESC")
    ArrayList<Review> selectByHighRate();//별점 높은 순

    @Select("SELECT * FROM review ORDER BY star")
    ArrayList<Review> selectByLowRate();//별점 낮은 순

    @Select("SELECT * FROM review WHERE memberNo=#{memberNo}")
    Review selectByMember(@Param("memberNo") int memberNo);//멤버별 후기

    @Update("UPDATE review SET content=#{content}, star=#{star} WHERE reviewNo=#{reviewNo}")
    void update(Review r);//후기 수정

    @Delete("DELETE FROM review WHERE reviewNo=#{reviewNo}")
    void delete(@Param("reviewNo") int reviewNo);//후기 삭제
}
