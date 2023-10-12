package dao;

import data.testentity.Highway;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface HighwayDAO {
    @Insert("insert into highway values(#{highwayCd}, #{highwayNm}, #{direction})")
    void insert(Highway h);

    @Select("select * from highway where highwayCd=#{highwayCd}")
    Highway selectByNo(@Param("highwayCd") String highwayCd);

    @Select("select * from highway")
    ArrayList<Highway> selectAll();

    @Delete("delete from highway where highwayCd=#{highwayCd}")
    void delete(@Param("highwayCd") String highwayCd);
}
