package com.cyberwise.workanalysis.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class WorkInfoForWeekExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WorkInfoForWeekExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNull() {
            addCriterion("realname is null");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNotNull() {
            addCriterion("realname is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameEqualTo(String value) {
            addCriterion("realname =", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotEqualTo(String value) {
            addCriterion("realname <>", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThan(String value) {
            addCriterion("realname >", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("realname >=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThan(String value) {
            addCriterion("realname <", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThanOrEqualTo(String value) {
            addCriterion("realname <=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLike(String value) {
            addCriterion("realname like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotLike(String value) {
            addCriterion("realname not like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameIn(List<String> values) {
            addCriterion("realname in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotIn(List<String> values) {
            addCriterion("realname not in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameBetween(String value1, String value2) {
            addCriterion("realname between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotBetween(String value1, String value2) {
            addCriterion("realname not between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andWeekCapacityIsNull() {
            addCriterion("week_capacity is null");
            return (Criteria) this;
        }

        public Criteria andWeekCapacityIsNotNull() {
            addCriterion("week_capacity is not null");
            return (Criteria) this;
        }

        public Criteria andWeekCapacityEqualTo(Double value) {
            addCriterion("week_capacity =", value, "weekCapacity");
            return (Criteria) this;
        }

        public Criteria andWeekCapacityNotEqualTo(Double value) {
            addCriterion("week_capacity <>", value, "weekCapacity");
            return (Criteria) this;
        }

        public Criteria andWeekCapacityGreaterThan(Double value) {
            addCriterion("week_capacity >", value, "weekCapacity");
            return (Criteria) this;
        }

        public Criteria andWeekCapacityGreaterThanOrEqualTo(Double value) {
            addCriterion("week_capacity >=", value, "weekCapacity");
            return (Criteria) this;
        }

        public Criteria andWeekCapacityLessThan(Double value) {
            addCriterion("week_capacity <", value, "weekCapacity");
            return (Criteria) this;
        }

        public Criteria andWeekCapacityLessThanOrEqualTo(Double value) {
            addCriterion("week_capacity <=", value, "weekCapacity");
            return (Criteria) this;
        }

        public Criteria andWeekCapacityIn(List<Double> values) {
            addCriterion("week_capacity in", values, "weekCapacity");
            return (Criteria) this;
        }

        public Criteria andWeekCapacityNotIn(List<Double> values) {
            addCriterion("week_capacity not in", values, "weekCapacity");
            return (Criteria) this;
        }

        public Criteria andWeekCapacityBetween(Double value1, Double value2) {
            addCriterion("week_capacity between", value1, value2, "weekCapacity");
            return (Criteria) this;
        }

        public Criteria andWeekCapacityNotBetween(Double value1, Double value2) {
            addCriterion("week_capacity not between", value1, value2, "weekCapacity");
            return (Criteria) this;
        }

        public Criteria andConsumedWeekCapacityIsNull() {
            addCriterion("consumed_week_capacity is null");
            return (Criteria) this;
        }

        public Criteria andConsumedWeekCapacityIsNotNull() {
            addCriterion("consumed_week_capacity is not null");
            return (Criteria) this;
        }

        public Criteria andConsumedWeekCapacityEqualTo(Double value) {
            addCriterion("consumed_week_capacity =", value, "consumedWeekCapacity");
            return (Criteria) this;
        }

        public Criteria andConsumedWeekCapacityNotEqualTo(Double value) {
            addCriterion("consumed_week_capacity <>", value, "consumedWeekCapacity");
            return (Criteria) this;
        }

        public Criteria andConsumedWeekCapacityGreaterThan(Double value) {
            addCriterion("consumed_week_capacity >", value, "consumedWeekCapacity");
            return (Criteria) this;
        }

        public Criteria andConsumedWeekCapacityGreaterThanOrEqualTo(Double value) {
            addCriterion("consumed_week_capacity >=", value, "consumedWeekCapacity");
            return (Criteria) this;
        }

        public Criteria andConsumedWeekCapacityLessThan(Double value) {
            addCriterion("consumed_week_capacity <", value, "consumedWeekCapacity");
            return (Criteria) this;
        }

        public Criteria andConsumedWeekCapacityLessThanOrEqualTo(Double value) {
            addCriterion("consumed_week_capacity <=", value, "consumedWeekCapacity");
            return (Criteria) this;
        }

        public Criteria andConsumedWeekCapacityIn(List<Double> values) {
            addCriterion("consumed_week_capacity in", values, "consumedWeekCapacity");
            return (Criteria) this;
        }

        public Criteria andConsumedWeekCapacityNotIn(List<Double> values) {
            addCriterion("consumed_week_capacity not in", values, "consumedWeekCapacity");
            return (Criteria) this;
        }

        public Criteria andConsumedWeekCapacityBetween(Double value1, Double value2) {
            addCriterion("consumed_week_capacity between", value1, value2, "consumedWeekCapacity");
            return (Criteria) this;
        }

        public Criteria andConsumedWeekCapacityNotBetween(Double value1, Double value2) {
            addCriterion("consumed_week_capacity not between", value1, value2, "consumedWeekCapacity");
            return (Criteria) this;
        }

        public Criteria andCplIsNull() {
            addCriterion("cpl is null");
            return (Criteria) this;
        }

        public Criteria andCplIsNotNull() {
            addCriterion("cpl is not null");
            return (Criteria) this;
        }

        public Criteria andCplEqualTo(Double value) {
            addCriterion("cpl =", value, "cpl");
            return (Criteria) this;
        }

        public Criteria andCplNotEqualTo(Double value) {
            addCriterion("cpl <>", value, "cpl");
            return (Criteria) this;
        }

        public Criteria andCplGreaterThan(Double value) {
            addCriterion("cpl >", value, "cpl");
            return (Criteria) this;
        }

        public Criteria andCplGreaterThanOrEqualTo(Double value) {
            addCriterion("cpl >=", value, "cpl");
            return (Criteria) this;
        }

        public Criteria andCplLessThan(Double value) {
            addCriterion("cpl <", value, "cpl");
            return (Criteria) this;
        }

        public Criteria andCplLessThanOrEqualTo(Double value) {
            addCriterion("cpl <=", value, "cpl");
            return (Criteria) this;
        }

        public Criteria andCplIn(List<Double> values) {
            addCriterion("cpl in", values, "cpl");
            return (Criteria) this;
        }

        public Criteria andCplNotIn(List<Double> values) {
            addCriterion("cpl not in", values, "cpl");
            return (Criteria) this;
        }

        public Criteria andCplBetween(Double value1, Double value2) {
            addCriterion("cpl between", value1, value2, "cpl");
            return (Criteria) this;
        }

        public Criteria andCplNotBetween(Double value1, Double value2) {
            addCriterion("cpl not between", value1, value2, "cpl");
            return (Criteria) this;
        }

        public Criteria andPttIsNull() {
            addCriterion("ptt is null");
            return (Criteria) this;
        }

        public Criteria andPttIsNotNull() {
            addCriterion("ptt is not null");
            return (Criteria) this;
        }

        public Criteria andPttEqualTo(Double value) {
            addCriterion("ptt =", value, "ptt");
            return (Criteria) this;
        }

        public Criteria andPttNotEqualTo(Double value) {
            addCriterion("ptt <>", value, "ptt");
            return (Criteria) this;
        }

        public Criteria andPttGreaterThan(Double value) {
            addCriterion("ptt >", value, "ptt");
            return (Criteria) this;
        }

        public Criteria andPttGreaterThanOrEqualTo(Double value) {
            addCriterion("ptt >=", value, "ptt");
            return (Criteria) this;
        }

        public Criteria andPttLessThan(Double value) {
            addCriterion("ptt <", value, "ptt");
            return (Criteria) this;
        }

        public Criteria andPttLessThanOrEqualTo(Double value) {
            addCriterion("ptt <=", value, "ptt");
            return (Criteria) this;
        }

        public Criteria andPttIn(List<Double> values) {
            addCriterion("ptt in", values, "ptt");
            return (Criteria) this;
        }

        public Criteria andPttNotIn(List<Double> values) {
            addCriterion("ptt not in", values, "ptt");
            return (Criteria) this;
        }

        public Criteria andPttBetween(Double value1, Double value2) {
            addCriterion("ptt between", value1, value2, "ptt");
            return (Criteria) this;
        }

        public Criteria andPttNotBetween(Double value1, Double value2) {
            addCriterion("ptt not between", value1, value2, "ptt");
            return (Criteria) this;
        }

        public Criteria andWeekStartDateIsNull() {
            addCriterion("week_start_date is null");
            return (Criteria) this;
        }

        public Criteria andWeekStartDateIsNotNull() {
            addCriterion("week_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andWeekStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("week_start_date =", value, "weekStartDate");
            return (Criteria) this;
        }

        public Criteria andWeekStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("week_start_date <>", value, "weekStartDate");
            return (Criteria) this;
        }

        public Criteria andWeekStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("week_start_date >", value, "weekStartDate");
            return (Criteria) this;
        }

        public Criteria andWeekStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("week_start_date >=", value, "weekStartDate");
            return (Criteria) this;
        }

        public Criteria andWeekStartDateLessThan(Date value) {
            addCriterionForJDBCDate("week_start_date <", value, "weekStartDate");
            return (Criteria) this;
        }

        public Criteria andWeekStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("week_start_date <=", value, "weekStartDate");
            return (Criteria) this;
        }

        public Criteria andWeekStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("week_start_date in", values, "weekStartDate");
            return (Criteria) this;
        }

        public Criteria andWeekStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("week_start_date not in", values, "weekStartDate");
            return (Criteria) this;
        }

        public Criteria andWeekStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("week_start_date between", value1, value2, "weekStartDate");
            return (Criteria) this;
        }

        public Criteria andWeekStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("week_start_date not between", value1, value2, "weekStartDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}