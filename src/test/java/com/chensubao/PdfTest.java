package com.chensubao;

import com.chensubao.util.pdf.*;
import org.junit.Test;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PdfTest {

    @Test
    public void generateAndAdd() throws Exception {
        String pdfDir = "E:\\generate\\pdf\\";
        String pdfName = "test.pdf";
        String templateDir = "/templates/pdf";
        String templateName = "test.ftl";
        String filePath = PdfUtil.generatePDF(null, pdfDir, pdfName, templateDir, templateName);
        File file = new File(filePath);
        int skipPage = 2;

        // 添加页码
        // PdfWriteUtil.addPageNumbers(file, new PageNumberEntry().setSkipPage(skipPage));
        List<String> titles = Arrays.asList("一、标题1", "二、标题2", "1.标题2-1", "2.标题2-2", "三、标题3", "结尾");
        // 添加目录对应的页码，先获取标题对应的页码
        Map<String, List<Integer>> wordsPage = PdfUtil.wordsPage(titles, file, skipPage);
        // 添加目录页码
        MenuNavPageEntry menuNavPageEntry = new MenuNavPageEntry()
                .setSkipPage(0)
                .setOffsetY(630)
                .setOffsetX(85)
                .setStride(17.3F);
        PdfWriteUtil.addMenuNavPage(file, wordsPage, titles, menuNavPageEntry);
    }
}
