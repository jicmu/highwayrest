package dao.master;

import data.entity.Highway;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HighwayDao {
    @Insert("insert into highway values(seq_highway_no.nextval, #{routeNo}, #{routeCd}, #{routeNm})")
    void insert(Highway highway);

    @Select("select routeCd from highway where routeCd = #{no}")
    String select(@Param("no") String no);
}
