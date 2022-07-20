package com.accounting.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * @author Xiangbin HAN (hanxb2001@163.com)
 * @author Nils Hartmann
 */
public class CustomerOrder {

    private OrderField field;
    private OrderType order;



    @JsonProperty("field")
    public OrderField getField() {
        return field;
    }

    public void setField(OrderField field) {
        this.field = field;
    }

    @JsonProperty("order")
    public OrderType getOrder() {
        return order;
    }

    public void setOrder(OrderType order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return this.field + " " + this.order;
    }

    public Sort.Order toOrder() {
        Sort.Direction direction = order == null ? Sort.DEFAULT_DIRECTION : order == OrderType.ASC ? Sort.Direction.ASC : Sort.Direction.DESC;
        return new Sort.Order(direction, field.name());
    }

    /**
     * @param orders
     * @return String
     */
    public static String buildOrderJdbcQuery(List<CustomerOrder> orders) {
        StringBuilder sb = new StringBuilder();

        Optional<List<CustomerOrder>> nonNullOrders = Optional.ofNullable(orders);
        nonNullOrders.ifPresent(list -> {
            sb.append(" order by");
            list.forEach(order -> {
                if (order.getField().equals(OrderField.name))
                    sb.append(" name " + order.getOrder() + ",");
                else
                    sb.append(" " + order.getField() + " " + order.getOrder() + ",");
            });
        });

        if(sb.indexOf("order by") > 0)
            return sb.substring(0, sb.lastIndexOf(","));
        else
            return "";
    }

    /**
     * @param orders
     * @return String
     */
    public static String buildOrderJpaQuery(List<CustomerOrder> orders) {
        StringBuilder sb = new StringBuilder();

        Optional<List<CustomerOrder>> nonNullOrders = Optional.ofNullable(orders);
        nonNullOrders.ifPresent(list ->
            {sb.append(" order by");
                list.forEach(order -> sb.append(" owner." + order.getField() + " " + order.getOrder() + ","));}
        );

        if(sb.indexOf("order by") > 0)
            return sb.substring(0, sb.lastIndexOf(","));
        else
            return "";
    }


}
