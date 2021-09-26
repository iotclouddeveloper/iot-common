package com.hvisions.common.dto;

public class Pageable
{
    private Sort sort;
    private Integer pageSize;
    private Integer pageNumber;
    private Integer offset;
    private Boolean unpaged;
    private Boolean paged;
    
    public Sort getSort() {
        return this.sort;
    }
    
    public Integer getPageSize() {
        return this.pageSize;
    }
    
    public Integer getPageNumber() {
        return this.pageNumber;
    }
    
    public Integer getOffset() {
        return this.offset;
    }
    
    public Boolean getUnpaged() {
        return this.unpaged;
    }
    
    public Boolean getPaged() {
        return this.paged;
    }
    
    public void setSort(final Sort sort) {
        this.sort = sort;
    }
    
    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }
    
    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
    
    public void setOffset(final Integer offset) {
        this.offset = offset;
    }
    
    public void setUnpaged(final Boolean unpaged) {
        this.unpaged = unpaged;
    }
    
    public void setPaged(final Boolean paged) {
        this.paged = paged;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Pageable)) {
            return false;
        }
        final Pageable other = (Pageable)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$sort = this.getSort();
        final Object other$sort = other.getSort();
        Label_0065: {
            if (this$sort == null) {
                if (other$sort == null) {
                    break Label_0065;
                }
            }
            else if (this$sort.equals(other$sort)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$pageSize = this.getPageSize();
        final Object other$pageSize = other.getPageSize();
        Label_0102: {
            if (this$pageSize == null) {
                if (other$pageSize == null) {
                    break Label_0102;
                }
            }
            else if (this$pageSize.equals(other$pageSize)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$pageNumber = this.getPageNumber();
        final Object other$pageNumber = other.getPageNumber();
        Label_0139: {
            if (this$pageNumber == null) {
                if (other$pageNumber == null) {
                    break Label_0139;
                }
            }
            else if (this$pageNumber.equals(other$pageNumber)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$offset = this.getOffset();
        final Object other$offset = other.getOffset();
        Label_0176: {
            if (this$offset == null) {
                if (other$offset == null) {
                    break Label_0176;
                }
            }
            else if (this$offset.equals(other$offset)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$unpaged = this.getUnpaged();
        final Object other$unpaged = other.getUnpaged();
        Label_0213: {
            if (this$unpaged == null) {
                if (other$unpaged == null) {
                    break Label_0213;
                }
            }
            else if (this$unpaged.equals(other$unpaged)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$paged = this.getPaged();
        final Object other$paged = other.getPaged();
        if (this$paged == null) {
            if (other$paged == null) {
                return true;
            }
        }
        else if (this$paged.equals(other$paged)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof Pageable;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $sort = this.getSort();
        result = result * 59 + (($sort == null) ? 43 : $sort.hashCode());
        final Object $pageSize = this.getPageSize();
        result = result * 59 + (($pageSize == null) ? 43 : $pageSize.hashCode());
        final Object $pageNumber = this.getPageNumber();
        result = result * 59 + (($pageNumber == null) ? 43 : $pageNumber.hashCode());
        final Object $offset = this.getOffset();
        result = result * 59 + (($offset == null) ? 43 : $offset.hashCode());
        final Object $unpaged = this.getUnpaged();
        result = result * 59 + (($unpaged == null) ? 43 : $unpaged.hashCode());
        final Object $paged = this.getPaged();
        result = result * 59 + (($paged == null) ? 43 : $paged.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "Pageable(sort=" + this.getSort() + ", pageSize=" + this.getPageSize() + ", pageNumber=" + this.getPageNumber() + ", offset=" + this.getOffset() + ", unpaged=" + this.getUnpaged() + ", paged=" + this.getPaged() + ")";
    }
}
