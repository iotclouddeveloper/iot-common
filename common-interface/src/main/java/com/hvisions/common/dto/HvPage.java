package com.hvisions.common.dto;

import io.swagger.annotations.*;
import java.util.*;

@ApiModel(description = "分页")
public class HvPage<T>
{
    private List<T> content;
    private Pageable pageable;
    private Boolean last;
    private Integer totalElements;
    private Integer totalPages;
    private Boolean first;
    private Sort sort;
    private Integer numberOfElements;
    private Integer size;
    private Integer number;
    
    public List<T> getContent() {
        return this.content;
    }
    
    public Pageable getPageable() {
        return this.pageable;
    }
    
    public Boolean getLast() {
        return this.last;
    }
    
    public Integer getTotalElements() {
        return this.totalElements;
    }
    
    public Integer getTotalPages() {
        return this.totalPages;
    }
    
    public Boolean getFirst() {
        return this.first;
    }
    
    public Sort getSort() {
        return this.sort;
    }
    
    public Integer getNumberOfElements() {
        return this.numberOfElements;
    }
    
    public Integer getSize() {
        return this.size;
    }
    
    public Integer getNumber() {
        return this.number;
    }
    
    public void setContent(final List<T> content) {
        this.content = content;
    }
    
    public void setPageable(final Pageable pageable) {
        this.pageable = pageable;
    }
    
    public void setLast(final Boolean last) {
        this.last = last;
    }
    
    public void setTotalElements(final Integer totalElements) {
        this.totalElements = totalElements;
    }
    
    public void setTotalPages(final Integer totalPages) {
        this.totalPages = totalPages;
    }
    
    public void setFirst(final Boolean first) {
        this.first = first;
    }
    
    public void setSort(final Sort sort) {
        this.sort = sort;
    }
    
    public void setNumberOfElements(final Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }
    
    public void setSize(final Integer size) {
        this.size = size;
    }
    
    public void setNumber(final Integer number) {
        this.number = number;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HvPage)) {
            return false;
        }
        final HvPage<?> other = (HvPage<?>)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        Label_0065: {
            if (this$content == null) {
                if (other$content == null) {
                    break Label_0065;
                }
            }
            else if (this$content.equals(other$content)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$pageable = this.getPageable();
        final Object other$pageable = other.getPageable();
        Label_0102: {
            if (this$pageable == null) {
                if (other$pageable == null) {
                    break Label_0102;
                }
            }
            else if (this$pageable.equals(other$pageable)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$last = this.getLast();
        final Object other$last = other.getLast();
        Label_0139: {
            if (this$last == null) {
                if (other$last == null) {
                    break Label_0139;
                }
            }
            else if (this$last.equals(other$last)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$totalElements = this.getTotalElements();
        final Object other$totalElements = other.getTotalElements();
        Label_0176: {
            if (this$totalElements == null) {
                if (other$totalElements == null) {
                    break Label_0176;
                }
            }
            else if (this$totalElements.equals(other$totalElements)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$totalPages = this.getTotalPages();
        final Object other$totalPages = other.getTotalPages();
        Label_0213: {
            if (this$totalPages == null) {
                if (other$totalPages == null) {
                    break Label_0213;
                }
            }
            else if (this$totalPages.equals(other$totalPages)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$first = this.getFirst();
        final Object other$first = other.getFirst();
        Label_0250: {
            if (this$first == null) {
                if (other$first == null) {
                    break Label_0250;
                }
            }
            else if (this$first.equals(other$first)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$sort = this.getSort();
        final Object other$sort = other.getSort();
        Label_0287: {
            if (this$sort == null) {
                if (other$sort == null) {
                    break Label_0287;
                }
            }
            else if (this$sort.equals(other$sort)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$numberOfElements = this.getNumberOfElements();
        final Object other$numberOfElements = other.getNumberOfElements();
        Label_0324: {
            if (this$numberOfElements == null) {
                if (other$numberOfElements == null) {
                    break Label_0324;
                }
            }
            else if (this$numberOfElements.equals(other$numberOfElements)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$size = this.getSize();
        final Object other$size = other.getSize();
        Label_0361: {
            if (this$size == null) {
                if (other$size == null) {
                    break Label_0361;
                }
            }
            else if (this$size.equals(other$size)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$number = this.getNumber();
        final Object other$number = other.getNumber();
        if (this$number == null) {
            if (other$number == null) {
                return true;
            }
        }
        else if (this$number.equals(other$number)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof HvPage;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $content = this.getContent();
        result = result * 59 + (($content == null) ? 43 : $content.hashCode());
        final Object $pageable = this.getPageable();
        result = result * 59 + (($pageable == null) ? 43 : $pageable.hashCode());
        final Object $last = this.getLast();
        result = result * 59 + (($last == null) ? 43 : $last.hashCode());
        final Object $totalElements = this.getTotalElements();
        result = result * 59 + (($totalElements == null) ? 43 : $totalElements.hashCode());
        final Object $totalPages = this.getTotalPages();
        result = result * 59 + (($totalPages == null) ? 43 : $totalPages.hashCode());
        final Object $first = this.getFirst();
        result = result * 59 + (($first == null) ? 43 : $first.hashCode());
        final Object $sort = this.getSort();
        result = result * 59 + (($sort == null) ? 43 : $sort.hashCode());
        final Object $numberOfElements = this.getNumberOfElements();
        result = result * 59 + (($numberOfElements == null) ? 43 : $numberOfElements.hashCode());
        final Object $size = this.getSize();
        result = result * 59 + (($size == null) ? 43 : $size.hashCode());
        final Object $number = this.getNumber();
        result = result * 59 + (($number == null) ? 43 : $number.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "HvPage(content=" + this.getContent() + ", pageable=" + this.getPageable() + ", last=" + this.getLast() + ", totalElements=" + this.getTotalElements() + ", totalPages=" + this.getTotalPages() + ", first=" + this.getFirst() + ", sort=" + this.getSort() + ", numberOfElements=" + this.getNumberOfElements() + ", size=" + this.getSize() + ", number=" + this.getNumber() + ")";
    }
}
