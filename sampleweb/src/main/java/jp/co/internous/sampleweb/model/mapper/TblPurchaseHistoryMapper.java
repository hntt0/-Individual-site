package jp.co.internous.sampleweb.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.sampleweb.model.domain.dto.PurchaseHistoryDto;

@Mapper
public interface TblPurchaseHistoryMapper {
	
		int insert(Map<String, Object> parameter);
		
		@Select("SELECT history.purchased_at, product.product_name, product.price, history.product_count,"
				+ " destination.family_name, destination.first_name, destination.address FROM"
				+ " tbl_purchase_history history JOIN mst_product product ON history.product_id = product.id"
				+ " JOIN mst_destination destination ON history.destination_id = destination.id"
				+ " WHERE history.user_id = #{userId} AND history.status = 1")
		List<PurchaseHistoryDto> findByUserId(@Param("userId") int userId);
		
		@Update("UPDATE tbl_purchase_history SET status = 0 WHERE user_id = #{userId}")
		int findByDeletehistory(@Param("userId") int userId);
		
		
	
}
