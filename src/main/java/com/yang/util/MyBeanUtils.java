package com.yang.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

public class MyBeanUtils {
    public static String[] getNullPro(Object source){
        BeanWrapper beanWrapper=new BeanWrapperImpl(source);
        PropertyDescriptor[] pds=beanWrapper.getPropertyDescriptors();
        List<String> nullPro=new ArrayList<>();
        for (PropertyDescriptor pd : pds){
            String proName=pd.getName();
            if (beanWrapper.getPropertyValue(proName)==null){
                nullPro.add(proName);
            }
        }
        return nullPro.toArray(new String[nullPro.size()]);
    }
}
