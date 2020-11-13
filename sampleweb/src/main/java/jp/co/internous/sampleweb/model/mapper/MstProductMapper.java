package jp.co.internous.sampleweb.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.internous.sampleweb.model.domain.MstProduct;

@Mapper
public interface MstProductMapper {
	
	@Select("SELECT * FROM mst_product")
	List<MstProduct> productAll();
	
	@Select("SELECT * FROM mst_product WHERE category_id = #{categoryId}")
	List<MstProduct> findByCategoryId(@Param("categoryId") int categoryId);

//	@Select("SELECT * FROM mst_product WHERE product_name LIKE '%${productName}%'")
	List<MstProduct> findByProductName(@Param("productName") List<String> productName);
	
	@Select("SELECT * FROM mst_product where id = #{id}")
	MstProduct findById(@Param("id") int id);
	
	@Select("SELECT * FROM mst_product where category_id = #{categoryId} AND product_name LIKE '%${productNames}%'")
	List<MstProduct> findByCategoryIdProductName(@Param("categoryId") int categoryId, @Param("productNames") List<String> productNames);
	

}
