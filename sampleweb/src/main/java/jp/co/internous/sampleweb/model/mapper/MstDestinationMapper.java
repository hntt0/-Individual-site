package jp.co.internous.sampleweb.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.sampleweb.model.domain.MstDestination;

@Mapper
public interface MstDestinationMapper {
	
	@Insert("INSERT INTO mst_destination("
			+ "user_id, family_name, first_name, tel_number, address) VALUES("
			+ "#{userId}, #{familyName}, #{firstName}, #{address}, #{telNumber})")
	void insert(MstDestination destination);
	
	@Select("SELECT MAX(id) FROM mst_destination WHERE user_id = #{userId}")
	int findById(int userId);
	
	@Select("SELECT * FROM mst_destination"
			+ " WHERE user_id = #{userId} AND status = 1 ORDER BY id ASC")
	List<MstDestination> findByUserId(@Param("userId") int userId);
	
	@Update("UPDATE mst_destination SET status = 0, updated_at = now() WHERE id = #{id}")
	int deleteById(@Param("id") int id);
	
	@Select("SELECT id FROM mst_destination WHERE user_id = #{userId} ORDER BY created_at DESC LIMIT 0, 1")
	int findIdByUserId(@Param("userId") int userId);
	
	
}