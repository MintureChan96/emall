package cn.minture.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.minture.emall.dao.TbSpecificationMapper;
import cn.minture.emall.entity.TbSpecification;
import cn.minture.emall.entity.TbSpecificationExample;
import cn.minture.result.ShopResult;
import cn.minture.seller.service.SpecificationService;

@Service
public class SpecificationServiceImpl implements SpecificationService{

	@Autowired
	private TbSpecificationMapper specificationMapper;
	
	
	@Override
	public void add(TbSpecification specification) {
		specificationMapper.insert(specification);
		
	}

	@Override
	public void update(TbSpecification specification) {
		specificationMapper.updateByExample(specification, null);
		
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id :ids) {
		specificationMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public TbSpecification findById(Long id) {
		
		return specificationMapper.selectByPrimaryKey(id);
	}

	@Override
	public ShopResult queryByPager(TbSpecification specification, int pageNo, int pageSize) {
		TbSpecificationExample example = new TbSpecificationExample();
		if(specification!=null)
		{
			if(specification.getSpecName()!=null&&!"".equals(specification.getSpecName()))
			{
				example.createCriteria().andSpecNameLike("%"+specification.getSpecName()+"%");
				
			}
		}
		PageHelper.startPage(pageNo,pageSize);
		Page<TbSpecification> page =(Page<TbSpecification>)specificationMapper.selectByExample(example);
		Map<String,Object> list= new HashMap<String,Object>();
		list.put("total", page.getTotal());
		list.put("rows",page.getResult());
		return ShopResult.ok(list);
	}

	@Override
	public List<TbSpecification> queryAll() {
		return specificationMapper.selectByExample(null);
	}
	
	

}
