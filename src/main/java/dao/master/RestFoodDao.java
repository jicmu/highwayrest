package dao.master;

import data.entity.RestFood;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface RestFoodDao {
    @Insert("insert into restfood(no, name, stdRestCd, foodCost) values(seq_restfood_no.nextval, #{name}, #{stdRestCd}, #{foodCost})")
    void insert(RestFood restFood);

    @Select("select * from restfood where stdRestCd = #{stdRestCd}")
    ArrayList<RestFood> selectBySCd(@Param("stdRestCd") String stdRestCd);

    @Select("select * from restfood where name like #{name} and stdRestCd = #{stdRestCd}")
    ArrayList<RestFood> selectByName(@Param("name") String name, @Param("stdRestCd") String stdRestCd);
}
