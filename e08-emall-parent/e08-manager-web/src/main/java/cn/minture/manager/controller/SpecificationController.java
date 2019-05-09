package cn.minture.manager.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import cn.minture.emall.entity.TbSpecification;
import cn.minture.result.ShopResult;
import cn.minture.seller.service.SpecificationService;

@RestController
@RequestMapping("/specify")
public class SpecificationController {

	@Reference
	private SpecificationService specificationService;	
	
    @RequestMapping("/findByPager")	
     public ShopResult queryByPager(@RequestBody TbSpecification specification,int pageNo,int pageSize)
     {
    	 
    	 return specificationService.queryByPager(specification, pageNo, pageSize);
     }
    
    @RequestMapping("/findById")
    public TbSpecification queryById(Long id) {

    	return specificationService.findById(id);
	}
    
    @RequestMapping("/add")
    public ShopResult add(@RequestBody TbSpecification specification)
    {
    	try {
    		specificationService.add(specification);
    		return ShopResult.build(200,"添加成功!");
			  
		} catch (Exception e) {
			return ShopResult.build(500,"添加失败!");
		}
    	
    }
    
    @RequestMapping("/delete")
    public ShopResult delete(Long [] ids)
    {
    	try {
    		specificationService.delete(ids);
    		return ShopResult.build(200,"删除成功!");
		} catch (Exception e) {
			return ShopResult.build(500,"删除失败!");
		}
    }
    
    @RequestMapping("/update")
    public ShopResult update(@RequestBody TbSpecification specification)
    {
    	try {
    		specificationService.update(specification);
    		return ShopResult.build(200,"修改成功!");
		} catch (Exception e) {
			return ShopResult.build(500,"修改失败!");
		}
    }
}
