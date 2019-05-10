package cn.minture.seller.service;

import java.util.List;
import java.util.Map;

import cn.minture.emall.entity.Specification;
import cn.minture.emall.entity.TbSpecification;
import cn.minture.result.ShopResult;

public interface SpecificationService {

	 public void add(Specification specification);
	 public void update(Specification specification);
	 public void delete(Long [] ids);
	 public Specification findById(Long id);
	 public ShopResult queryByPager(TbSpecification specification,int pageNo,int pageSize);
	 public List<TbSpecification> queryAll();
	 public List<Map>selectOptionList();
}
