package com.egoumall.common.mapper;

import com.egoumall.common.pojo.OrderShopping;
import com.egoumall.common.pojo.OrderShoppingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderShoppingMapper {
    long countByExample(OrderShoppingExample example);

    int deleteByExample(OrderShoppingExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(OrderShopping record);

    int insertSelective(OrderShopping record);

    List<OrderShopping> selectByExample(OrderShoppingExample example);

    OrderShopping selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") OrderShopping record, @Param("example") OrderShoppingExample example);

    int updateByExample(@Param("record") OrderShopping record, @Param("example") OrderShoppingExample example);

    int updateByPrimaryKeySelective(OrderShopping record);

    int updateByPrimaryKey(OrderShopping record);
}