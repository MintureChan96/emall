package cn.minture.seller.service;

import java.util.List;

import cn.minture.emall.entity.TbSpecification;
import cn.minture.result.ShopResult;

public interface SpecificationService {

	 public void add(TbSpecification specification);
	 public void update(TbSpecification specification);
	 public void delete(Long [] ids);
	 public TbSpecification findById(Long id);
	 public ShopResult queryByPager(TbSpecification specification,int pageNo,int pageSize);
	 public List<TbSpecification> queryAll();
}
