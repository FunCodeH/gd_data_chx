package com.gd.datachx.mapper;

import com.gd.datachx.entity.TRmVipInfo;

import java.util.List;

public interface TRmVipInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_rm_vip_info
     *
     * @mbggenerated
     */
    int insert(TRmVipInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_rm_vip_info
     *
     * @mbggenerated
     */
    int insertSelective(TRmVipInfo record);

    List<TRmVipInfo> selectVipInfo();
}