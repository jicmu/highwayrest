package dao.member;

import data.entity.Member;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface MemberDao {
	@Insert("insert into member values(seq_member.nextval, #{id}, #{password}, #{name}, #{nickname}, #{email}, #{tel})")
	void insert(Member m);
	
	@Select("select * from member")
	ArrayList<Member> selectAll();
	
	@Select("select * from member where id=#{id}")
	Member select(@Param("id") String id);
	
	@Update("update member set password=#{password}, nickname=#{nickname} where id=#{id}")
	void update(Member m);
	
	@Delete("delete from member where id=#{id}")
	void delete(@Param("id") String id);
}
