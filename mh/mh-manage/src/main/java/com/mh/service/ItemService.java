package com.mh.service;



import com.mh.vo.EasyUI_Tree;
import com.mh.vo.SysResult;

import java.util.List;

import com.mh.pojo.Item;
import com.mh.vo.EasyUI_Table;


public interface ItemService {

	EasyUI_Table findItemByPage(Integer page, Integer rows);

	List<EasyUI_Tree> findItemCatByParentId(Long parentId);

	void saveItem(Item item);

	void updateItem(Item item);

	void deleteItems(Long[] ids);

	void updateStatus(Long[] ids, int status);


	
}
