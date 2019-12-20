package com.chensubao.util.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

public class PdfWriteUtil {

    /**
     * 添加页码
     * @param pageNumberEntry 入参对象
     * @throws IOException io exception
     */
    public static void addPageNumbers(File file, PageNumberEntry pageNumberEntry) throws IOException {
        // 加载pdf
        PDDocument document = PDDocument.load(file);
        // 页码计数器
        int page_counter = 1;
        // 加载宋体
        PDType0Font font = PDType0Font.load(document, new ClassPathResource("static/fonts/simsun.ttf").getInputStream());
        for (PDPage page : document.getPages()) {
            if (page_counter <= pageNumberEntry.getSkipPage()) {
                ++ page_counter;
                continue;
            }
            // 获取当页的操作流
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, false);
            contentStream.beginText();
            contentStream.setFont(font, 10);
            PDRectangle pageSize = page.getMediaBox();
            float x = pageSize.getLowerLeftX();
            float y = pageSize.getLowerLeftY();
            contentStream.newLineAtOffset(x + pageSize.getWidth() - pageNumberEntry.getOffsetX(), y + pageNumberEntry.getOffsetY());
            String text = MessageFormat.format(pageNumberEntry.getNumberingFormat(), page_counter - pageNumberEntry.getSkipPage(), document.getNumberOfPages() - pageNumberEntry.getSkipPage());
            contentStream.showText(text);
            contentStream.endText();
            contentStream.close();
            ++ page_counter;
        }
        // 保存到源文件
        document.save(new File(file.getPath()));
        document.close();
    }

    /**
     * 添加目录对应的页码，右下角开始
     * 写入方式为一页一页写入，如有多页外层自行循环调用（暂时如此，也可根据此方法自行封装）
     * 偏移量与titles可自行传入，titles与获取关键词对应页码需相同
     * @param file pdf文件
     * @param wordsPage 关键词对应的页码集合
     * @param menuNavPageEntry 入参对象
     * @throws Exception 异常
     */
    public static void addMenuNavPage(File file, Map<String, List<Integer>> wordsPage, List<String> titles, MenuNavPageEntry menuNavPageEntry) throws Exception {
        if (file.getTotalSpace() == 0) {
            throw new IllegalArgumentException("pdf文件错误");
        }
        if (file.isDirectory()) {
            throw new IllegalArgumentException("pdf文件不能是一个目录");
        }
        Assert.notEmpty(wordsPage, "关键词页码不能为空");
        // 获取标题对应页码时用到的记录同个标题多个页码的计数器
        Map<String, Integer> titleCounter = new HashMap<>(16);
        // 加载pdf
        PDDocument document = PDDocument.load(file);
        // 获取对应的一页
        PDPage page = document.getPage(menuNavPageEntry.getPageIndex());
        // 加载字体 宋体
        PDType0Font font = PDType0Font.load(document, new ClassPathResource("static/fonts/simsun.ttf").getInputStream());
        // 获取pdf单页操作流
        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.PREPEND, false);
        // 设置字体可支持中文
        contentStream.setFont(font, 10);
        // 获取页面媒体
        PDRectangle pageSize = page.getMediaBox();

        // 页码倒序集合 wordsPage => desc int list
        List<Integer> integers = new ArrayList<>();
        for (String title : titles) {
            // 查找params中是否已有对应title的取值次数（索引）
            int index = null == titleCounter.get(title) ? 0 : (titleCounter.get(title) + 1);
            titleCounter.put(title, index);
            // 获取title对应的页码
            if (CollectionUtils.isEmpty(wordsPage.get(title))) {
                throw new IllegalStateException("标题：" + title + "对应的页码为空");
            }
            integers.add(wordsPage.get(title).get(index) - menuNavPageEntry.getSkipPage());
        }
        // 由于是右下角开始，需要将页码倒序
        integers.sort(Collections.reverseOrder());
        // 写完一个页码后下个页码相隔多少距离
        float pageNumStride = 0;
        for (Integer integer : integers) {
            // 开始写入页码
            showTextByLeft(contentStream, integer.toString(), "", menuNavPageEntry.getOffsetX(),menuNavPageEntry.getOffsetY() + pageNumStride, pageSize);
            pageNumStride += menuNavPageEntry.getStride();
        }

        // 关闭流
        contentStream.close();
        // 保存至源文件
        document.save(new File(file.getPath()));
        // 关闭pdf对象
        document.close();
    }

    /**
     * 往pdf中写内容
     * @throws Exception
     */
    private static void showTextByLeft(PDPageContentStream overContent, String txt, String def, float offset_x, float offset_y, PDRectangle pageSize) throws Exception{
        float x = pageSize.getLowerLeftX();
        float y = pageSize.getLowerLeftY();
        // Begin the Content stream
        overContent.beginText();
        overContent.newLineAtOffset(x + pageSize.getWidth() - offset_x, y + offset_y);
        // Setting the position for the line
        overContent.newLineAtOffset(x, y);
        // Adding text in the form of string
        overContent.showText(null == txt ? def : txt);
        // Ending the content stream
        overContent.endText();
    }
}
