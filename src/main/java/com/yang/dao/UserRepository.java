package com.yang.dao;

import com.yang.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    //方法名称规则查询：是对jpql的更加深入的封装，我们只需要按照springDataJpa提供的
    //方法名称规则定义方法，不需要再去配置jpql语句就可以完成查询
    //命名规则：方法名的约定：
    //以fingBy开头 ：表示查询
    //fingBy后面加对象中的属性名（首字母大写），表示查询条件
    //运行时，程序会把findBy翻译成：from xxx
    //模糊查询时的命名规则：fingBy属性名称(首字母大写)Like
    //例如：findByUsernameLike表示按照username模糊查询
    //findByUsernameIsNull表示查询Username不为空的用户信息
    //findByUsernameLikeAndAndEmail表示模糊匹配Username和精准匹配email进行交集查询
    User findByUsernameAndPassword(String username,String password);//参数位置要对应
    //User findByUsernameLikeAndAndEmail(String username,String email);
}
