package dao.master;

import data.entity.RestFood;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FoodDao {
    @Insert("insert into restfood(no, name, stdRestCd) values(seq_restfood_no.nextval, #{name}, #{stdRestCd})")
    void insert(RestFood restFood);
}
