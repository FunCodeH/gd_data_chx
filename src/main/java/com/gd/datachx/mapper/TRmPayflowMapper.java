package com.gd.datachx.mapper;

import com.gd.datachx.entity.TRmPayflow;

import java.util.Date;
import java.util.List;

public interface TRmPayflowMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_rm_payflow
     *
     * @mbggenerated
     */
    int insert(TRmPayflow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_rm_payflow
     *
     * @mbggenerated
     */
    int insertSelective(TRmPayflow record);

    List<TRmPayflow> selectOrderDetail(Date orderDate);
}