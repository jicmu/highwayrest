package dao.master;

import data.entity.RestFood;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RestFoodDao {
    @Insert("insert into restfood(no, name, stdRestCd, foodCost) values(seq_restfood_no.nextval, #{name}, #{stdRestCd}, #{foodCost})")
    void insert(RestFood restFood);
}
