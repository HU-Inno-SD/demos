package nl.hu.sd.inno.basicboot.shop.data;

import nl.hu.sd.inno.basicboot.shop.domain.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class CachingProductRepository implements ProductRepository {
    private final ProductRepository inner;

    public CachingProductRepository(ProductRepository inner) {
        this.inner = inner;
    }

    private List<Product> cachedProducts = null;
    private LocalDateTime cacheTime = null;


    public boolean shouldRefresh() {
        if (cachedProducts == null) return true;
        if (cacheTime == null) return true;
        if (cacheTime.isBefore(LocalDateTime.now().minus(300, ChronoUnit.SECONDS))) return true;
        return false;
    }

    public void refresh() {
        this.cachedProducts = inner.findAll();
        this.cacheTime = LocalDateTime.now();
    }

    public void clearCache(){
        this.cachedProducts = null;
        this.cacheTime = null;
    }

    @Override
    public List<Product> findAll() {
        //Luie demo code: eigenlijk moeten we in alle lees-methodes de cache checken
        if (shouldRefresh()) {
            refresh();
        }

        return this.cachedProducts;
    }

    @Override
    public List<Product> findAll(Sort sort) {

        return inner.findAll(sort);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {

        return inner.findAll(pageable);
    }

    @Override
    public List<Product> findAllById(Iterable<Long> longs) {
        ;
        return inner.findAllById(longs);
    }

    @Override
    public long count() {
        ;
        return inner.count();
    }

    @Override
    public void deleteById(Long aLong) {
        ;
        inner.deleteById(aLong);
    }

    @Override
    public void delete(Product entity) {
        ;
        inner.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        ;
        inner.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Product> entities) {
        ;
        inner.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        ;
        inner.deleteAll();
    }

    @Override
    public <S extends Product> S save(S entity) {
        clearCache();
        return inner.save(entity);
    }

    @Override
    public <S extends Product> List<S> saveAll(Iterable<S> entities) {
        ;
        return inner.saveAll(entities);
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        //Luie demo code: eigenlijk moeten we in alle lees-methodes de cache checken
        if (shouldRefresh()) {
            refresh();
        }

        return this.cachedProducts.stream().filter(p -> p.getId().equals(aLong)).findFirst();
    }

    @Override
    public boolean existsById(Long aLong) {
        ;
        return inner.existsById(aLong);
    }

    @Override
    public void flush() {
        ;
        inner.flush();
    }

    @Override
    public <S extends Product> S saveAndFlush(S entity) {
        ;
        return inner.saveAndFlush(entity);
    }

    @Override
    public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
        ;
        return inner.saveAllAndFlush(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Product> entities) {
        ;
        inner.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        ;
        inner.deleteAllByIdInBatch(longs);
    }

    @Override
    public void deleteAllInBatch() {
        ;
        inner.deleteAllInBatch();
    }

    @Override
    public Product getOne(Long aLong) {
        ;
        return inner.getReferenceById(aLong);
    }

    @Override
    public Product getById(Long aLong) {
        ;
        return inner.getById(aLong);
    }

    @Override
    public Product getReferenceById(Long aLong) {
        ;
        return inner.getReferenceById(aLong);
    }

    @Override
    public <S extends Product> Optional<S> findOne(Example<S> example) {
        ;
        return inner.findOne(example);
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example) {
        ;
        return inner.findAll(example);
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
        ;
        return inner.findAll(example, sort);
    }

    @Override
    public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
        ;
        return inner.findAll(example, pageable);
    }

    @Override
    public <S extends Product> long count(Example<S> example) {
        ;
        return inner.count(example);
    }

    @Override
    public <S extends Product> boolean exists(Example<S> example) {
        ;
        return inner.exists(example);
    }

    @Override
    public <S extends Product, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        ;
        return inner.findBy(example, queryFunction);
    }
}
