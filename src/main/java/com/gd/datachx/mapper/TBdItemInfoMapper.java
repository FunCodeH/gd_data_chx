package com.gd.datachx.mapper;

import com.gd.datachx.entity.TBdItemInfo;
import com.gd.datachx.entity.TitemStockInfo;

import java.util.Date;
import java.util.List;

public interface TBdItemInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bd_item_info
     *
     * @mbggenerated
     */
    int insert(TBdItemInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bd_item_info
     *
     * @mbggenerated
     */
    int insertSelective(TBdItemInfo record);

    List<TitemStockInfo> selectItemInfo();
}