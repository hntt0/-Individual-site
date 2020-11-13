package jp.co.internous.sampleweb.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.sampleweb.model.domain.dto.PurchaseHistoryDto;

@Mapper
public interface TblPurchaseHistoryMapper {
	
		int insert(Map<String, Object> parameter);
		
		List<PurchaseHistoryDto> findByUserId(@Param("userId") int userId);
		
		@Update("UPDATE tbl_purchase_history SET status = 0 WHERE user_id = #{userId}")
		int findByDeletehistory(@Param("userId") int userId);
		
		
	
}
