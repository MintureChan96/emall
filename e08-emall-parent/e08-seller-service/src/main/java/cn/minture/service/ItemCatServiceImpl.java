package cn.minture.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.minture.emall.dao.TbItemCatMapper;
import cn.minture.emall.entity.TbItemCat;
import cn.minture.result.ShopResult;
import cn.minture.seller.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public List<TbItemCat> findAll() {
		
		return itemCatMapper.selectByExample(null);
	}

	@Override
	public ShopResult queryByPage(TbItemCat itemCat, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		Page<TbItemCat> page = (Page<TbItemCat>)itemCatMapper.selectByExample(null);
		Map<String,Object> list =new HashMap<String,Object>();
		list.put("total",page.getTotal());
		list.put("rows",page.getResult());
		return ShopResult.ok(list);
	}

	@Override
	public void add(TbItemCat itemCat) {
		itemCatMapper.insert(itemCat);
		
	}

	@Override
	public void update(TbItemCat itemCat) {
		itemCatMapper.updateByPrimaryKey(itemCat);
		
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id:ids)
		{
		itemCatMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public TbItemCat queryById(Long id) {
		
		return itemCatMapper.selectByPrimaryKey(id);
	}

}
