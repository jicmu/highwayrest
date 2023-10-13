package dao.master;

import data.dto.HighwayRestDTO;
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

    @Select("select distinct road.routenm roadName, rest.svarnm restName from highway road, highwayrest rest where road.routecd = rest.routecd and road.routeCd = #{no}")
    ArrayList<HighwayRestDTO> joinCheck(@Param("no") String no);
}
