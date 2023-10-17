package dao.member;

import data.entity.Member;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface MemberDao {
	@Insert("insert into member values(seq_member.nextval, #{id}, #{password}, #{name}, #{nickname}, LOWER(#{email}), #{tel})")
	void insert(Member m);

	@Select("select * from member")
	ArrayList<Member> selectAll();

	@Select("select * from member where id=#{id}")
	Member select(@Param("id") String id);

	@Select("select * from member where email=LOWER(#{email})")
	Member selectByEmail(@Param("email") String email);

	@Select("select * from member where nickname=#{nickname}")
	Member selectByNickname(@Param("nickname") String nickname);

	@Select("select * from member where tel=#{tel}")
	Member selectByTel(@Param("tel") String tel);

	@Update("update member set password=#{password}, nickname=#{nickname}, tel=#{tel} where id=#{id}")
	void update(Member m);

	@Delete("delete from member where id=#{id}")
	void delete(@Param("id") String id);
}