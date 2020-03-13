package com.yang.dao;

import com.yang.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    //方法名称规则查询，是对jpql的更加深入的封装，我们只需要按照springDataJpa提供的
    //方法名称规则定义方法，不需要再去配置jpql语句就可以完成查询
    //命名规则：方法名的约定：
              //以fingBy开头 ：查询
              //对象中的属性名（首字母大写），表示查询条件
    List<Comment> findByBlogIdAndParentCommentNull(Long id, Sort sort);
}
