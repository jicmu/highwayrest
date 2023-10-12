package dao;

import data.testentity.Rest;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface RestDAO {
    @Insert("insert into rest values(#{restCd}, #{restNm}, #{restAddr}, #{highwayCd}, #{smallPark}, #{largePark}, #{disabledPark}," +
            "#{tel})")
    void insert(Rest r);

    @Select("select * from rest where restCd=#{restCd}")
    Rest selectByRest(@Param("restCd") String restCd);

    @Select("select * from rest where highwayCd=#{highwayCd}")
    ArrayList<Rest> selectHighway(@Param("highwayCd") String highwayCd);

    @Delete("delete from rest where restCd=#{restCd}")
    void delete(@Param("restCd") String restCd);
}
