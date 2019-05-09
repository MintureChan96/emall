package cn.minture.manager.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.minture.emall.entity.TbItemCat;
import cn.minture.result.ShopResult;
import cn.minture.seller.service.ItemCatService;

@RestController
@RequestMapping("/item")
public class ItemCatController {

	@Reference
	private ItemCatService itemService;
	
	@RequestMapping("queryByPager")
	public ShopResult queryByPager(@RequestBody TbItemCat itemCat,int size,int page)
	{
		
		return itemService.queryByPage(itemCat, page, size);
	}
	
	
}
