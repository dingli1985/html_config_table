package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import entity.UDict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
public interface UDictMapper extends BaseMapper<UDict> {
	

}
