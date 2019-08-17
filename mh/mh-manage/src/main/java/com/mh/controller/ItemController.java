package com.mh.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mh.pojo.Item;
import com.mh.service.ItemService;
import com.mh.vo.EasyUI_Table;
import com.mh.vo.EasyUI_Tree;
import com.mh.vo.SysResult;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/query")
	public EasyUI_Table findItemByPage(Integer page,Integer rows) {
		
		return itemService.findItemByPage(page,rows);
	}
	@RequestMapping("/cat/list")
	public List<EasyUI_Tree> findItemCatByParentId(
			@RequestParam(name = "id",defaultValue = "0")
			Long parentId) {
		return itemService.findItemCatByParentId(parentId);
	}
	
	@RequestMapping("/save")
	public SysResult saveItem(Item item) {
		try {
			itemService.saveItem(item);
			 return SysResult.success();
		} catch (Exception e) {
			// TODO: handle exception
			return SysResult.fail();
		} 
	}
	@RequestMapping("/update")
	public SysResult updateItem(Item item) {

		itemService.updateItem(item);
		return SysResult.success();
		//由于已经编辑统一异常处理.
		//所以不需要考虑异常
	}
	@RequestMapping("/delete")
	public SysResult deleteItems(Long[] ids) {
		itemService.deleteItems(ids);
		return SysResult.success();
	}
	@RequestMapping("/instock")
	public SysResult itemInstock(Long[] ids) {
		int status = 2;	//表示下架
		itemService.updateStatus(ids,status);
		return SysResult.success();
	}
	
	/**
	 * 商品上架
	 */
	@RequestMapping("/reshelf")
	public SysResult itemReshelf(Long[] ids) {
		int status = 1;	//表示上架
		itemService.updateStatus(ids,status);
		return SysResult.success();
	}

	
}
