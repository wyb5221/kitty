package com.louis.kitty.admin.dto;

import com.louis.kitty.admin.model.SysMenuApi;

import java.io.Serializable;
import java.util.List;

/**
 * @author: bxguo
 * @time: 2019/11/8 14:29
 */
public class BindRequest implements Serializable {

    private static final long serialVersionUID = 7011316817276055767L;

    private List<SysMenuApi> bindList;

    public List<SysMenuApi> getBindList() {
        return bindList;
    }

    public void setBindList(List<SysMenuApi> bindList) {
        this.bindList = bindList;
    }
}
