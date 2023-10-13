package dao.master;

import data.entity.Highway;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface HighwayDao {
    @Insert("insert into highway values(seq_highway_no.nextval, #{routeNo}, #{routeCd}, #{routeNm})")
    void insert(Highway highway);

    @Select("select routeCd from highway where routeCd = #{no}")
    String select(@Param("no") String no);

    @Select("select * from highway where routeCd = #{routeCd}")
    Highway selectByCd(@Param("routeCd") String routeCd);

    @Select("select * from highway where routeNm like #{routeNm}")
    ArrayList<Highway> selectByNm(@Param("routeNm") String routeNm);

    @Select("select * from highway")
    ArrayList<Highway> selectAll();
}
