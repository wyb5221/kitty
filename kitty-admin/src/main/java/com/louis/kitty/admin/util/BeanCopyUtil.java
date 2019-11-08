package com.louis.kitty.admin.util;


import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: bxguo
 * @time: 2019/11/08 14:55
 */
public class BeanCopyUtil {
    private BeanCopyUtil() {
    }

    public static <F,T>T copy(F from, Class<T> destCls){
        if (from == null) {
            return null;
        }

        if (from.getClass().equals(destCls)) {
            return (T) from;
        }
        return JSON.parseObject(JSON.toJSONString(from), destCls);
    }

    public static <F,T> List<T> copyList(List<F> from, Class<T> destCls){
        if (CollectionUtils.isEmpty(from) ) {
            return Collections.<T>emptyList();
        }
        if (from.get(0).getClass().equals(destCls)) {
            return (List<T>) from;
        }
        return from.stream().map(f -> copy(f, destCls)).collect(Collectors.toList());
    }
}
