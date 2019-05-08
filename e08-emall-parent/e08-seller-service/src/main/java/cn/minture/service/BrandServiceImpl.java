package cn.minture.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.minture.emall.dao.TbBrandMapper;
import cn.minture.emall.entity.TbBrand;
import cn.minture.emall.entity.TbBrandExample;
import cn.minture.emall.entity.TbBrandExample.Criteria;
import cn.minture.result.ShopResult;
import cn.minture.seller.service.BrandService;

//该Service注解是用于dubbo的服务发现 因此是dubbo包注解
@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	private TbBrandMapper tbBrandMapper;
	/**
	 * 品牌全查
	 */
	@Override
	public List<TbBrand> findAll() {
		
		return tbBrandMapper.selectByExample(null);
	}
	
	/**
	 * 带分页的条件查询
	 * @param tbBrand pageNo,pageSize;
	 */
	@Override
	public ShopResult queryByPage(TbBrand tbBrand, int pageNo, int pageSize) {
		TbBrandExample example = new TbBrandExample();
		Criteria c = example.createCriteria();
		if(tbBrand!=null)
		{
			if(tbBrand.getName()!=null && !"".equals(tbBrand.getName()))
			{
				c.andNameLike("%"+tbBrand.getName()+"%");
				
			}
			if(tbBrand.getFirstChar()!=null && !"".equals(tbBrand.getFirstChar()))
			{
				c.andFirstCharEqualTo(tbBrand.getFirstChar());
			}
		}
	     PageHelper.startPage(pageNo,pageSize);
	     Page<TbBrand> page1 =(Page<TbBrand>)
	     tbBrandMapper.selectByExample(example);
	     Map<String,Object> list = new HashMap<String,Object>();
	     list.put("total", page1.getTotal());
	     list.put("rows",page1.getResult());
	     return ShopResult.ok(list);
	}
	/**
	 * 添加品牌
	 * @param tbBrand
	 */
	@Override
	public void add(TbBrand tbBrand) {
		tbBrandMapper.insert(tbBrand);
		
	}
	/**
	 * 修改品牌 
	 * @param tbBrand 
	 */
	@Override
	public void update(TbBrand tbBrand) {
		tbBrandMapper.updateByExample(tbBrand, null);
		
	}
	/**
	 * 删除品牌
	 * @param ids 长整型数组
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id: ids)
		{
			tbBrandMapper.deleteByPrimaryKey(id);
		}
		
		
	}
	/**
	 * 根据Id查询品牌 
	 * @param id
	 */
	@Override
	public TbBrand queryById(Long id) {
		return tbBrandMapper.selectByPrimaryKey(id);
	}

}
