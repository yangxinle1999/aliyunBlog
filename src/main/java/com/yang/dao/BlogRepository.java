package com.yang.dao;

import com.yang.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//JpaSpecificationExecutor里面定义的是复杂查询（比如分页等）
public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {
    @Query(value = "select b from Blog b where b.recommend = true ")//自定义jpql语句（jpa query language）
    //@Query还有一个nativeQuery属性，表示查询方式，默认为false，支持的是jpql查询，即查询的字段是
    //实体类中的属性字段，表是实体类的名称；改为true之后，支持的是sql查询，就和普通的一致了
    List<Blog> findTop(Pageable pageable);

    @Query(value = "select b from Blog b where b.title like  ?1")
    Page<Blog> findByQuery(String query,Pageable pageable);

    @Transactional  //jpa在进行修改和删除操作时要加上事务，否则会报错
    @Modifying  //用于声明此方法是用于更新操作的
    @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);

    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year desc ")
    List<String> findGroupYear();

    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Blog> findByYear(String year);
}
