package com.idream.commons.lib.dto.activity;

/**
 * @author charles.wei
 */
public class QueryPageDto {

    private Integer currentPage;
    private Integer pageSize;
    private String query;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

}
