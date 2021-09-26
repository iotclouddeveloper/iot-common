package com.hvisions.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.Objects;

public class PageInfo
{
    @ApiModelProperty("页数，默认0,第一页")
    private int page;
    @ApiModelProperty("每页个数,默认10")
    private int pageSize;
    @ApiModelProperty("是否排序,默认不排序")
    private boolean sort;
    @ApiModelProperty("是否正序,默认正序")
    private boolean direction;
    @ApiModelProperty("排序列名")
    private String sortCol;
    
    public PageInfo() {
        this.page = 0;
        this.pageSize = 10;
        this.sort = false;
        this.direction = true;
    }
    
    public PageInfo(final int page, final int pageSize, final boolean sort, final String sortCol) {
        this.page = 0;
        this.pageSize = 10;
        this.sort = false;
        this.direction = true;
        this.page = page;
        this.pageSize = pageSize;
        this.sort = sort;
        this.sortCol = sortCol;
    }
    
    @JsonIgnore
    public PageRequest getRequest() {
        if (this.sort) {
            return PageRequest.of(this.page, this.pageSize, Sort.by(this.direction ? Sort.Direction.ASC : Sort.Direction.DESC, new String[] { this.sortCol }));
        }
        return PageRequest.of(this.page, this.pageSize);
    }
    
    public int getPage() {
        return this.page;
    }
    
    public int getPageSize() {
        return this.pageSize;
    }
    
    public boolean isSort() {
        return this.sort;
    }
    
    public boolean isDirection() {
        return this.direction;
    }
    
    public String getSortCol() {
        return this.sortCol;
    }
    
    public void setPage(final int page) {
        this.page = page;
    }
    
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }
    
    public void setSort(final boolean sort) {
        this.sort = sort;
    }
    
    public void setDirection(final boolean direction) {
        this.direction = direction;
    }
    
    public void setSortCol(final String sortCol) {
        this.sortCol = sortCol;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof PageInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageInfo pageInfo = (PageInfo) o;
        return page == pageInfo.page &&
                pageSize == pageInfo.pageSize &&
                sort == pageInfo.sort &&
                direction == pageInfo.direction &&
                Objects.equals(sortCol, pageInfo.sortCol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, sort, direction, sortCol);
    }

    @Override
    public String toString() {
        return "PageInfo(page=" + this.getPage() + ", pageSize=" + this.getPageSize() + ", sort=" + this.isSort() + ", direction=" + this.isDirection() + ", sortCol=" + this.getSortCol() + ")";
    }
}
