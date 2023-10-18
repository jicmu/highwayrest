package dao.search;

import data.entity.Search;
import oracle.jdbc.proxy.annotation.Post;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.ArrayList;

@Mapper
public interface SearchDao {

    @Insert("insert into search values(seq_search_no.nextval, #{searchType}, #{memberNo}, sysdate, #{searchWord})")
    void insert(Search s);

    @Select("select * from search where memberNo = #{memberNo}")
    ArrayList<Search> selectByMember(@Param("memberNo") int memberNo);

    @Select("select * from search where searchType = #{searchType}")
    ArrayList<Search> selectByType(@Param("searchType") int searchType);

    @Select("select * from search where searchDate = #{searchDate}")
    ArrayList<Search> selectByDate(@Param("searchDate") Date searchDate);

    @Delete("delete from search where searchNo = #{searchNo}")
    void delete(@Param("searchNo") int searchNo);
}
