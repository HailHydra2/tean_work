package com.fzu.teamwork.model;

import java.util.ArrayList;
import java.util.List;

public class ReportResponseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public ReportResponseExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
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

        public Criteria andReportorIdIsNull() {
            addCriterion("reportor_id is null");
            return (Criteria) this;
        }

        public Criteria andReportorIdIsNotNull() {
            addCriterion("reportor_id is not null");
            return (Criteria) this;
        }

        public Criteria andReportorIdEqualTo(Integer value) {
            addCriterion("reportor_id =", value, "reportorId");
            return (Criteria) this;
        }

        public Criteria andReportorIdNotEqualTo(Integer value) {
            addCriterion("reportor_id <>", value, "reportorId");
            return (Criteria) this;
        }

        public Criteria andReportorIdGreaterThan(Integer value) {
            addCriterion("reportor_id >", value, "reportorId");
            return (Criteria) this;
        }

        public Criteria andReportorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reportor_id >=", value, "reportorId");
            return (Criteria) this;
        }

        public Criteria andReportorIdLessThan(Integer value) {
            addCriterion("reportor_id <", value, "reportorId");
            return (Criteria) this;
        }

        public Criteria andReportorIdLessThanOrEqualTo(Integer value) {
            addCriterion("reportor_id <=", value, "reportorId");
            return (Criteria) this;
        }

        public Criteria andReportorIdIn(List<Integer> values) {
            addCriterion("reportor_id in", values, "reportorId");
            return (Criteria) this;
        }

        public Criteria andReportorIdNotIn(List<Integer> values) {
            addCriterion("reportor_id not in", values, "reportorId");
            return (Criteria) this;
        }

        public Criteria andReportorIdBetween(Integer value1, Integer value2) {
            addCriterion("reportor_id between", value1, value2, "reportorId");
            return (Criteria) this;
        }

        public Criteria andReportorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reportor_id not between", value1, value2, "reportorId");
            return (Criteria) this;
        }

        public Criteria andResponseIdIsNull() {
            addCriterion("response_id is null");
            return (Criteria) this;
        }

        public Criteria andResponseIdIsNotNull() {
            addCriterion("response_id is not null");
            return (Criteria) this;
        }

        public Criteria andResponseIdEqualTo(Integer value) {
            addCriterion("response_id =", value, "responseId");
            return (Criteria) this;
        }

        public Criteria andResponseIdNotEqualTo(Integer value) {
            addCriterion("response_id <>", value, "responseId");
            return (Criteria) this;
        }

        public Criteria andResponseIdGreaterThan(Integer value) {
            addCriterion("response_id >", value, "responseId");
            return (Criteria) this;
        }

        public Criteria andResponseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("response_id >=", value, "responseId");
            return (Criteria) this;
        }

        public Criteria andResponseIdLessThan(Integer value) {
            addCriterion("response_id <", value, "responseId");
            return (Criteria) this;
        }

        public Criteria andResponseIdLessThanOrEqualTo(Integer value) {
            addCriterion("response_id <=", value, "responseId");
            return (Criteria) this;
        }

        public Criteria andResponseIdIn(List<Integer> values) {
            addCriterion("response_id in", values, "responseId");
            return (Criteria) this;
        }

        public Criteria andResponseIdNotIn(List<Integer> values) {
            addCriterion("response_id not in", values, "responseId");
            return (Criteria) this;
        }

        public Criteria andResponseIdBetween(Integer value1, Integer value2) {
            addCriterion("response_id between", value1, value2, "responseId");
            return (Criteria) this;
        }

        public Criteria andResponseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("response_id not between", value1, value2, "responseId");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(Integer value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(Integer value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(Integer value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(Integer value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(Integer value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Integer> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Integer> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(Integer value1, Integer value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }
    }

    /**
     */
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