package cn.minture.seller.service;

import java.util.List;

import cn.minture.emall.entity.TbBrand;
import cn.minture.result.ShopResult;

public interface BrandService {
	
  public List<TbBrand> findAll();
  public ShopResult queryByPage(TbBrand tbBrand,int pageNo,int pageSize);
  public void add(TbBrand tbBrand);
  public void update(TbBrand tbBrand);
  public void delete(Long ids[]);
  public TbBrand queryById(Long id);
}
