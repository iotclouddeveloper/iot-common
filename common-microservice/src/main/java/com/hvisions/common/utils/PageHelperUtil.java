package com.hvisions.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.*;
import com.hvisions.common.dto.PageInfo;
import org.springframework.data.domain.*;
import java.util.*;
import java.util.function.*;
import com.baomidou.mybatisplus.core.conditions.*;
import com.baomidou.mybatisplus.core.metadata.*;
import org.apache.commons.lang.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public class PageHelperUtil
{
    public static <P extends PageInfo, R> Page<R> getPage(final Function<P, List<R>> function, final P param) {
        return getPage(function, param, (Class<R>)null);
    }
    
    public static <P extends PageInfo, I, R> Page<R> getPage(final Function<P, List<I>> function, final P param, final Class<R> resultClass) {
        PageHelper.startPage(param.getRequest().getPageNumber() + 1, param.getRequest().getPageSize());
        if (param.getRequest().getSort().isSorted()) {
            final StringBuilder orderBy = new StringBuilder();
            for (final Sort.Order next : param.getRequest().getSort()) {
                final String property = next.getProperty();
                orderBy.append(property);
                orderBy.append(" ");
                if (next.getDirection().isAscending()) {
                    orderBy.append("ASC");
                }
                else {
                    orderBy.append("DESC");
                }
                orderBy.append(" ");
            }
            PageHelper.orderBy(orderBy.toString());
        }
        final List<I> result = function.apply(param);
        final com.github.pagehelper.PageInfo<I> pageInfo = (com.github.pagehelper.PageInfo<I>)new com.github.pagehelper.PageInfo((List)result);
        final PageImpl page = new PageImpl((List)result, (Pageable)param.getRequest(), pageInfo.getTotal());
        if (resultClass != null) {
            return DtoMapper.convertPage((Page)page, resultClass);
        }
        return (Page<R>)page;
    }
    
    public static <P extends PageInfo, I, R> Page getPage(final BiFunction<com.baomidou.mybatisplus.extension.plugins.pagination.Page<I>, Wrapper<I>, IPage<I>> function, final P param, final Wrapper<I> wrapper, final Class<R> resultClass) {
        final com.baomidou.mybatisplus.extension.plugins.pagination.Page<I> page = (com.baomidou.mybatisplus.extension.plugins.pagination.Page<I>)new com.baomidou.mybatisplus.extension.plugins.pagination.Page((long)(param.getPage() + 1), (long)param.getPageSize());
        final List<OrderItem> list = (List<OrderItem>)page.getOrders();
        if (param.getRequest().getSort().isSorted()) {
            final Iterator<Sort.Order> iterator = (Iterator<Sort.Order>)param.getRequest().getSort().iterator();
            while (iterator.hasNext()) {
                final OrderItem item = new OrderItem();
                final Sort.Order next = iterator.next();
                if (StringUtils.isNotBlank(param.getSortCol())) {
                    item.setColumn(param.getSortCol());
                    if (next.getDirection().isDescending()) {
                        item.setAsc(false);
                    }
                    else {
                        item.setAsc(true);
                    }
                    list.add(item);
                }
            }
        }
        page.setOrders((List)list);
        final IPage<I> result = function.apply(page, wrapper);
        final PageImpl<I> impl = (PageImpl<I>)new PageImpl(result.getRecords(), (Pageable)param.getRequest(), result.getTotal());
        return (Page)((resultClass != null) ? DtoMapper.convertPage((Page)impl, resultClass) : page);
    }
}
