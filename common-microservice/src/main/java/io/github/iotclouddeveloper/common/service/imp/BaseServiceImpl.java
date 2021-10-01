package io.github.iotclouddeveloper.common.service.imp;

import io.github.iotclouddeveloper.common.service.*;
import io.github.iotclouddeveloper.common.service.BaseService;
import org.springframework.data.repository.*;
import java.util.*;

@Deprecated
public class BaseServiceImpl<T, E> implements BaseService<T, E>
{
    private final PagingAndSortingRepository<T, E> pagingAndSortingRepository;
    
    public BaseServiceImpl(final PagingAndSortingRepository pagingAndSortingRepository) {
        this.pagingAndSortingRepository = (PagingAndSortingRepository<T, E>)pagingAndSortingRepository;
    }
    
    @Override
    public T findOne(final E id) {
        return this.pagingAndSortingRepository.findById(id).get();
    }
    
    @Override
    public T save(final T t) {
        return (T)this.pagingAndSortingRepository.save(t);
    }
    
    @Override
    public void delete(final E id) {
        this.pagingAndSortingRepository.deleteById(id);
    }
    
    @Override
    public List<T> findAll() {
        final Iterable<T> entitys = (Iterable<T>)this.pagingAndSortingRepository.findAll();
        final List<T> result = new ArrayList<T>();
        for (final T entity : entitys) {
            result.add(entity);
        }
        return result;
    }
    
    @Override
    public boolean exists(final E id) {
        return this.pagingAndSortingRepository.existsById(id);
    }
}
