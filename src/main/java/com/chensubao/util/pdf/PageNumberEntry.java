package com.chensubao.util.pdf;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PageNumberEntry {

    /**
     * 页码格式
     */
    private String numberingFormat = "第 {0} 页 / 共 {1} 页";

    /**
     * x轴偏移量
     */
    private float offsetX = 345;

    /**
     * y轴偏移量
     */
    private float offsetY = 10;

    /**
     * 跳过多少页
     */
    private int skipPage = 2;
}
