package com.chensubao.util.pdf;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class MenuNavPageEntry {

    /**
     * 获取pdf页，在那一页写目录页码
     * 从0开始
     */
    private int pageIndex = 1;

    /**
     * 下一个页码距离上一个页码多少距离
     */
    private float stride = 18;

    /**
     * 跳过多少页
     */
    private int skipPage = 2;

    /**
     * x轴偏移量
     */
    private float offsetX = 85;

    /**
     * y轴偏移量
     */
    private float offsetY = 750;
}
