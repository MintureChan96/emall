package cn.minture.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.minture.emall.dao.TbSpecificationMapper;
import cn.minture.emall.dao.TbSpecificationOptionMapper;
import cn.minture.emall.entity.Specification;
import cn.minture.emall.entity.TbSpecification;
import cn.minture.emall.entity.TbSpecificationExample;
import cn.minture.emall.entity.TbSpecificationOption;
import cn.minture.emall.entity.TbSpecificationOptionExample;
import cn.minture.emall.entity.TbSpecificationOptionExample.Criteria;
import cn.minture.result.ShopResult;
import cn.minture.seller.service.SpecificationService;

@Service
public class SpecificationServiceImpl implements SpecificationService{

	@Autowired
	private TbSpecificationMapper specificationMapper;
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;
	
	
	@Override
	public void add(Specification specification) {
		
		TbSpecification specification1 =specification.getSpecification();
		specificationMapper.insert(specification.getSpecification());
		List<TbSpecificationOption> list = specification.getSpecificationOption();
		for(TbSpecificationOption specificationOption:list)
		{
			specificationOption.setSpecId(specification1.getId());
			specificationOptionMapper.insert(specificationOption);
		}
	}

	@Override
	public void update(Specification specification) {
		TbSpecification tbSpecification = specification.getSpecification();
		specificationMapper.updateByPrimaryKey(tbSpecification);
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		Criteria c =example.createCriteria();
		c.andSpecIdEqualTo(specification.getSpecification().getId());
		specificationOptionMapper.deleteByExample(example);
			List<TbSpecificationOption> list = specification.getSpecificationOption();
			if(list!=null) {
			for(TbSpecificationOption specificationOption:list)
			{
				specificationOptionMapper.insert(specificationOption);
			}
		}
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id :ids) {
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		Criteria c =example.createCriteria();
		c.andSpecIdEqualTo(id);
	    specificationOptionMapper.deleteByExample(example);
	    specificationMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public Specification findById(Long id) {
		TbSpecification specification = specificationMapper.selectByPrimaryKey(id);
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		Criteria c =example.createCriteria();
		if(specification!=null)
		{
			c.andSpecIdEqualTo(specification.getId());
		}
		List<TbSpecificationOption> specificationOption = specificationOptionMapper.selectByExample(example);
		Specification specification1 =new Specification();
		specification1.setSpecification(specification);
		specification1.setSpecificationOption(specificationOption);
		return specification1;
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

	@Override
	public List<Map> selectOptionList() {
		return null;
	}
	
	

}
