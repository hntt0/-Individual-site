package jp.co.internous.sampleweb.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import jp.co.internous.sampleweb.model.domain.MstProduct;

@Mapper
public interface MstProductMapper {
	
	@Select("SELECT * FROM mst_product")
	List<MstProduct> productAll();
	
	@Select("SELECT * FROM mst_product WHERE id = ${id}")
	MstProduct destinationAll(int id);

}
