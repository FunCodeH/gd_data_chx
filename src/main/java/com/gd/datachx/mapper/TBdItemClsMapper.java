package com.gd.datachx.mapper;

import com.gd.datachx.entity.TBdItemCls;

import java.util.List;

public interface TBdItemClsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bd_item_cls
     *
     * @mbggenerated
     */
    int insert(TBdItemCls record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bd_item_cls
     *
     * @mbggenerated
     */
    int insertSelective(TBdItemCls record);

    List<TBdItemCls> selectItemCls();
}