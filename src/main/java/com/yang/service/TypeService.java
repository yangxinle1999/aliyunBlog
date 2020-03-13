package com.yang.service;

import com.yang.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeService {
    Type savaType(Type type);
    Type getType(Long id);
    Page<Type> listType(Pageable pageable);
    Type updateType(Long id,Type type);
    void deleteType(Long id);
    Type getTypeByName(String name);
    List<Type> listType();
    List<Type> listTypeTop(Integer size);//根据size大小决定index显示几个类别
    //void updateByTypeId(Long id);
}
