package br.com.gabrielferreira.usuarios.application.core.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class PageInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 8951227771618956898L;

    private Integer pageNumber;

    private Integer pageSize;

    private List<String[]> sortBy;

    public PageInfo(Integer pageNumber, Integer pageSize, List<String[]> sortBy) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
    }

    public PageInfo() {}

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<String[]> getSortBy() {
        return sortBy;
    }

    public void setSortBy(List<String[]> sortBy) {
        this.sortBy = sortBy;
    }
}
