package com.accounting.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

/**
 * @author Xiangbin HAN (hanxb2001@163.com)
 * @author Nils Hartmann
 */
@Getter
@Setter
public class CustomerFilter implements Specification<Customer> {

    private String name;
    private String address;
    private String city;
    private String telephone;


    /**
     * @return String
     */
    public String buildJpaQuery() {
        Optional<CustomerFilter> nonNullFilter = Optional.ofNullable(this);
        StringBuilder sb = new StringBuilder(" WHERE");
        sb.append(
            nonNullFilter.map(f -> f.getName())
                .map(item -> " customer.name LIKE :name and")
                .orElse("")
        );
        sb.append(
            nonNullFilter.map(f -> f.getAddress())
                .map(item -> " customer.address LIKE :address and")
                .orElse("")
        );
        sb.append(
            nonNullFilter.map(f -> f.getCity())
                .map(item -> " customer.city = :city and")
                .orElse("")
        );
        sb.append(
            nonNullFilter.map(f -> f.getTelephone())
                .map(item -> " customer.phone = :telephone and")
                .orElse("")
        );

        if(sb.indexOf(" and") > 0)
            return sb.substring(0, sb.lastIndexOf(" and"));
        else
            return "";

    }

    /**
     * @param query
     */
    public void buildJpaQueryParameters(Query query) {

        Optional<CustomerFilter> nonNullFilter = Optional.ofNullable(this);

        nonNullFilter.map(f -> f.getName()).ifPresent(item -> query.setParameter("name", item + "%"));
        nonNullFilter.map(f -> f.getAddress()).ifPresent(item -> query.setParameter("address", "%" + item + "%"));
        nonNullFilter.map(f -> f.getCity()).ifPresent(item -> query.setParameter("city", item));
        nonNullFilter.map(f -> f.getTelephone()).ifPresent(item -> query.setParameter("telephone", item));

    }

    /**
     * @return String
     */
    public String buildJdbcQuery() {
        Optional<CustomerFilter> nonNullFilter = Optional.ofNullable(this);
        StringBuilder sb = new StringBuilder(" WHERE");
        sb.append(
            nonNullFilter.map(f -> f.getName())
                .map(item -> " name LIKE :name and")
                .orElse("")
        );
        sb.append(
            nonNullFilter.map(f -> f.getAddress())
                .map(item -> " address LIKE :address and")
                .orElse("")
        );
        sb.append(
            nonNullFilter.map(f -> f.getCity())
                .map(item -> " city = :city and")
                .orElse("")
        );
        sb.append(
            nonNullFilter.map(f -> f.getTelephone())
                .map(item -> " telephone = :telephone and")
                .orElse("")
        );

        if(sb.indexOf(" and") > 0)
            return sb.substring(0, sb.lastIndexOf(" and"));
        else
            return "";

    }

    /**
     * @param params
     */
    public void buildJdbcQueryParameters(Map<String, Object> params) {

        Optional<CustomerFilter> nonNullFilter = Optional.ofNullable(this);

        nonNullFilter.map(f -> f.getName()).ifPresent(item -> params.put("name", item + "%"));
        nonNullFilter.map(f -> f.getAddress()).ifPresent(item -> params.put("address", "%" + item + "%"));
        nonNullFilter.map(f -> f.getCity()).ifPresent(item -> params.put("city", item));
        nonNullFilter.map(f -> f.getTelephone()).ifPresent(item -> params.put("telephone", item));
    }

    @Override
    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        var predicates = new LinkedList<Predicate>();

        if (isSet(name)) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }

        if (isSet(address)) {
            predicates.add(criteriaBuilder.like(root.get("address"), "%" + address + "%"));
        }

        if (isSet(city)) {
            predicates.add(criteriaBuilder.equal(root.get("city"), city));
        }

        if (isSet(telephone)) {
            predicates.add(criteriaBuilder.equal(root.get("telephone"), telephone));
        }

        if (predicates.isEmpty()) {
            return null;
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private boolean isSet(String s) {
        return s != null && !s.isBlank();
    }

}
