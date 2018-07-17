package com.chen.tian.papermgr.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页菜单栏显示对象
 * Created by ChenTian on 2018/5/11.
 */
@Getter
@Setter
public class MenuDto {
    private Long menuId;    //菜单唯一标识（主要用于刷新页面时定位菜单）
    private String name;    //菜单名称
    private String url;     //菜单跳转地址
    private boolean show;   //是否显示
    private List<MenuDto> subMenu = new ArrayList<>(0); //子菜单
    public MenuDto(){
    }
    public MenuDto(Long id, String name,String url, boolean show){
        this.menuId = id;
        this.name = name;
        this.url = url;
        this.show = show;
    }


}
