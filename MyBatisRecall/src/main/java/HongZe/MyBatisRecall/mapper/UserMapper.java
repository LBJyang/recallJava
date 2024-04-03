package HongZe.MyBatisRecall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import HongZe.MyBatisRecall.entity.User;

public interface UserMapper {
	@Select("select * from users where id = #{id}")
	User getById(@Param("id") long id);

	@Select("select * from users where email = #{email}")
	User getByEmail(@Param("email") String email);

	@Select("select * from users limit #{offset},#{maxResults}")
	List<User> getAll(@Param("offset") int offset, @Param("maxResults") int maxResults);

	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	@Insert("insert into users (email,password,name,createdAt) values (#{user.email},#{user.password},#{user.name},#{user.createdAt})")
	void insert(@Param("user") User user);

	@Update("update users set name = #{user.name},createdAt = #{user.createdAt} where id = #{user.id}")
	void update(@Param("user") User user);

	@Delete("delete from users where id = #{id}")
	void deleteById(@Param("id") long id);
}
