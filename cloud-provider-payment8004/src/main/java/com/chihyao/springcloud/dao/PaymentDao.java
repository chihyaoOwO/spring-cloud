package com.chihyao.springcloud.dao;

import com.chihyao.springcloud.entities.Payment;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface PaymentDao{

    @InsertProvider(type = PaymentSqlProvider.class, method = "create")
    int create(Payment payment);

    @SelectProvider(type = PaymentSqlProvider.class, method = "getPaymentById")
    Payment getPaymentById(@Param("id") String id);
}
