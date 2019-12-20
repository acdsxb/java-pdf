package com.chensubao.util.pdf;

import com.chensubao.util.ftl.FtlUtil;
import com.itextpdf.text.pdf.BaseFont;
import com.lowagie.text.DocumentException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * @author chensubao
 */
public class PdfUtil {

    private PdfUtil() {}

    private static final Logger log = LoggerFactory.getLogger(PdfUtil.class);

    /**
     * 根据freemarker生成PDF
     * @param data 数据model
     * @param pdfDir 生成pdf至哪个目录
     * @param pdfName pdf文件名
     * @param templateDir freemarker模板路径，只加载resources下的pdf目录，如需要其他方式请自定义修改
     * @param templateName freemarker模板文件名，需包含后缀名
     * @throws Exception 异常
     */
    public static String generatePDF(Object data, String pdfDir, String pdfName, String templateDir, String templateName) throws Exception {
        //生成路径
        genDir(pdfDir);
        FileOutputStream fileOut = new FileOutputStream(pdfDir + (pdfName.endsWith(".pdf") ? pdfName : (pdfName + ".pdf")));
        ITextRenderer render = getRender();
        render.setDocumentFromString(FtlUtil.freeMarkerToString(data, templateDir, templateName));
        render.layout();
        render.createPDF(fileOut,true);
        log.info("generate pdf done.");
        fileOut.flush();
        fileOut.close();
        return pdfDir + pdfName;
    }

    /**
     * 根据html生成pdf
     * @param filename 所生成的pdf文件名
     * @param htmlFile html文件
     * @throws Exception 异常
     */
    public static void generatePdfFormHtml(String filename, File htmlFile, String path)throws Exception{
        //生成路径
        genDir(path);
        FileOutputStream fileOut = new FileOutputStream(path + (filename.endsWith(".pdf") ? filename : (filename + ".pdf")));
        ITextRenderer render = getRender();
        render.setDocument(htmlFile);
        render.layout();
        render.createPDF(fileOut,true);
        log.info("generate pdf from html done.");
        fileOut.flush();
        fileOut.close();
    }

    /**
     * 获得关键词所在的页码
     * @param titles 关键词集合
     * @param file pdf文件
     * @param skipPage 跳过多少页
     * @return 关键词对应的页码集合
     * @throws IOException io exception
     */
    public static Map<String, List<Integer>> wordsPage(List<String> titles, File file, int skipPage) throws IOException {
        Assert.notEmpty(titles, "关键词不能为空");
        Assert.notNull(file, "pdf文件不能为空");
        if (file.getTotalSpace() == 0) {
            throw new IllegalArgumentException("pdf文件错误");
        }
        if (file.isDirectory()) {
            throw new IllegalArgumentException("pdf文件不能是一个目录");
        }
        PDDocument document = PDDocument.load(file);
        GetWordsPageStripper stripper = new GetWordsPageStripper(titles, skipPage);
        stripper.setSortByPosition(true);
        stripper.setStartPage(0);
        stripper.setEndPage(document.getNumberOfPages());
        Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
        stripper.writeText(document, dummy);
        document.close();
        return stripper.map();
    }

    /**
     * 初始化ITextRenderer
     * 并设置字体
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    private static ITextRenderer getRender() throws IOException, DocumentException {
        ITextRenderer render = new ITextRenderer();
        //添加字体库
        render.getFontResolver().addFont(new ClassPathResource("static/fonts/simsun.ttf").getPath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        render.getFontResolver().addFont(new ClassPathResource("static/fonts/msyh.ttf").getPath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        return render;
    }

    private static void genDir(String path) throws IOException {
        Path p = Paths.get(path);
        if (!Files.exists(p)) {
            Files.createDirectories(p);
        }
    }
}
