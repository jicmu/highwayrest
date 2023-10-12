package dao.master;

import data.entity.HighwayRest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface HighwayRestDao {
    @Insert("insert into highwayrest values(seq_highwayrest_no.nextval, #{svarCd}, #{svarNm}, #{gudClssCd}, #{rprsTelNo}, #{routeCd}, #{svarAddr})")
    void insert(HighwayRest item);

    @Select("select svarCd from highwayrest where svarCd = #{svarCd}")
    String select(@Param("svarCd") String svarCd);

    @Select("select * from highwayrest")
    ArrayList<HighwayRest> selectAll();
}
