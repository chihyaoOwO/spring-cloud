package com.chihyao.springcloud.dao;

import com.chihyao.springcloud.entities.Payment;
import org.apache.ibatis.jdbc.SQL;

public class PaymentSqlProvider {

    public final String create(Payment payment) {
        SQL sql = new SQL()
                .INSERT_INTO("payment")
                .INTO_COLUMNS("payment_id", "serial")
                .INTO_VALUES("'" + payment.getPayment_id() + "'", "'" + payment.getSerial() + "'");

        return sql.toString();
    }

    public final String getPaymentById() {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("payment")
                .WHERE("payment_id = #{id}");

        return sql.toString();
    }
}
