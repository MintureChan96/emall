package cn.minture.seller.service;

import java.util.List;

import cn.minture.emall.entity.TbItemCat;
import cn.minture.result.ShopResult;

public interface ItemCatService {

	public List<TbItemCat> findAll();
	  public ShopResult queryByPage(TbItemCat itemCat,int pageNo,int pageSize);
	  public void add(TbItemCat itemCat);
	  public void update(TbItemCat itemCat);
	  public void delete(Long ids[]);
	  public TbItemCat queryById(Long id);
	
}
