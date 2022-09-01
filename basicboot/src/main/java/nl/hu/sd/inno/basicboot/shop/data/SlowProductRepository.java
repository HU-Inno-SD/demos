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

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class SlowProductRepository implements ProductRepository {
    private final ProductRepository inner;
    private int delay = 5000;

    public SlowProductRepository(@Qualifier("productRepository") ProductRepository inner) {
        this.inner = inner;
    }

    private void delay() {
        try {
            Thread.sleep(this.delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAll() {
        delay();
        return inner.findAll();
    }

    @Override
    public List<Product> findAll(Sort sort) {
        delay();
        return inner.findAll(sort);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        delay();
        return inner.findAll(pageable);
    }

    @Override
    public List<Product> findAllById(Iterable<Long> longs) {
        delay();
        return inner.findAllById(longs);
    }

    @Override
    public long count() {
        delay();
        return inner.count();
    }

    @Override
    public void deleteById(Long aLong) {
        delay();
        inner.deleteById(aLong);
    }

    @Override
    public void delete(Product entity) {
        delay();
        inner.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        delay();
        inner.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Product> entities) {
        delay();
        inner.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        delay();
        inner.deleteAll();
    }

    @Override
    public <S extends Product> S save(S entity) {
        delay();
        return inner.save(entity);
    }

    @Override
    public <S extends Product> List<S> saveAll(Iterable<S> entities) {
        delay();
        return inner.saveAll(entities);
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        delay();
        return inner.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        delay();
        return inner.existsById(aLong);
    }

    @Override
    public void flush() {
        delay();
        inner.flush();
    }

    @Override
    public <S extends Product> S saveAndFlush(S entity) {
        delay();
        return inner.saveAndFlush(entity);
    }

    @Override
    public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
        delay();
        return inner.saveAllAndFlush(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Product> entities) {
        delay();
        inner.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        delay();
        inner.deleteAllByIdInBatch(longs);
    }

    @Override
    public void deleteAllInBatch() {
        delay();
        inner.deleteAllInBatch();
    }

    @Override
    public Product getOne(Long aLong) {
        delay();
        return inner.getReferenceById(aLong);
    }

    @Override
    public Product getById(Long aLong) {
        delay();
        return inner.getById(aLong);
    }

    @Override
    public Product getReferenceById(Long aLong) {
        delay();
        return inner.getReferenceById(aLong);
    }

    @Override
    public <S extends Product> Optional<S> findOne(Example<S> example) {
        delay();
        return inner.findOne(example);
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example) {
        delay();
        return inner.findAll(example);
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
        delay();
        return inner.findAll(example, sort);
    }

    @Override
    public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
        delay();
        return inner.findAll(example, pageable);
    }

    @Override
    public <S extends Product> long count(Example<S> example) {
        delay();
        return inner.count(example);
    }

    @Override
    public <S extends Product> boolean exists(Example<S> example) {
        delay();
        return inner.exists(example);
    }

    @Override
    public <S extends Product, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        delay();
        return inner.findBy(example, queryFunction);
    }
}
