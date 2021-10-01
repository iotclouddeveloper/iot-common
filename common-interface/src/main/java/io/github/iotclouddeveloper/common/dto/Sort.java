package io.github.iotclouddeveloper.common.dto;

public class Sort
{
    private Boolean sorted;
    private Boolean unsorted;
    
    public Boolean getSorted() {
        return this.sorted;
    }
    
    public Boolean getUnsorted() {
        return this.unsorted;
    }
    
    public void setSorted(final Boolean sorted) {
        this.sorted = sorted;
    }
    
    public void setUnsorted(final Boolean unsorted) {
        this.unsorted = unsorted;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Sort)) {
            return false;
        }
        final Sort other = (Sort)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$sorted = this.getSorted();
        final Object other$sorted = other.getSorted();
        Label_0065: {
            if (this$sorted == null) {
                if (other$sorted == null) {
                    break Label_0065;
                }
            }
            else if (this$sorted.equals(other$sorted)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$unsorted = this.getUnsorted();
        final Object other$unsorted = other.getUnsorted();
        if (this$unsorted == null) {
            if (other$unsorted == null) {
                return true;
            }
        }
        else if (this$unsorted.equals(other$unsorted)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof Sort;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $sorted = this.getSorted();
        result = result * 59 + (($sorted == null) ? 43 : $sorted.hashCode());
        final Object $unsorted = this.getUnsorted();
        result = result * 59 + (($unsorted == null) ? 43 : $unsorted.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "Sort(sorted=" + this.getSorted() + ", unsorted=" + this.getUnsorted() + ")";
    }
}
