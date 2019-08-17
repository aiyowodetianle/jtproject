package com.mh.vo;

import java.util.List;

import com.mh.pojo.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class EasyUI_Table {
	private Integer total;
	private List<Item> rows;
}
