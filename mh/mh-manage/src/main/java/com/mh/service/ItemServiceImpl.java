package com.mh.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.mapper.ItemMapper;
import com.mh.pojo.Item;
import com.mh.pojo.ItemCat;
import com.mh.vo.EasyUI_Table;
import com.mh.vo.EasyUI_Tree;
import com.mh.vo.SysResult;


@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemCatMapper itemCatMapper;
	@Override
	public EasyUI_Table findItemByPage(Integer page, Integer rows) {
		int total = itemMapper.selectCount(null);
		int start =(page-1)*rows;
		List<Item> itemList = itemMapper.selectList(start,total);
		return new EasyUI_Table(total,itemList);
	}


	@Override
	public List<EasyUI_Tree> findItemCatByParentId(Long parentId) {
		List<EasyUI_Tree> list=new ArrayList<EasyUI_Tree>();
		List<ItemCat> catList=findItemCatList(parentId);

		for(ItemCat itemCat:catList) {
			String text = itemCat.getName();
			Long id= itemCat.getId();
			String state= itemCat.getIsParent() ? "open" : "close";
			EasyUI_Tree tree=new EasyUI_Tree(id, text, state);
			list.add(tree);
		}
		return list;
	}


	public List<ItemCat> findItemCatList(Long parentId) {
			
		QueryWrapper<ItemCat> queryWrapper=new QueryWrapper<ItemCat>();
			queryWrapper.eq("parent_id", parentId);
			return  itemCatMapper.selectList(queryWrapper);
		
	}


	@Override
	@Transactional
	public void saveItem(Item item) {
		item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
		itemMapper.insert(item);
	}


	@Override
	public void updateItem(Item item) {
		// TODO Auto-generated method stub
		item.setUpdated(new Date());
		itemMapper.updateById(item);
	}


	@Override
	public void deleteItems(Long[] ids) {
		List<Long> idList = Arrays.asList(ids);
		itemMapper.deleteBatchIds(idList);
	}


	@Override
	public void updateStatus(Long[] ids, int status) {
	Item item= new Item();
	item.setStatus(status).setUpdated(new Date());
	UpdateWrapper<Item> updateWrapper= new UpdateWrapper<>();
		List<Long> idList = Arrays.asList(ids);
	updateWrapper.eq("id", idList);
		itemMapper.update(item, updateWrapper);//新建对象item更新，只更改修改的数据
		
	}



	
}
