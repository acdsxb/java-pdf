package com.chensubao.util.ftl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author chensubao
 */
public class FtlUtil {

    private FtlUtil() {}

    /**
     * ftl转html
     * @param data 需要合并的数据，可为空(需要在ftl中写好)
     * @param htmlName  生成的html文件的名称(包含路径在内)
     * @param basePackagePath 存放模板的路径，只加载resources下的pdf目录，如需要其他方式请自定义修改
     *                        e.g. /templates/pdf
     * @param templateName 所需要生成html的ftl文件模板，需包含后缀名
     * @throws Exception 异常
     */
    public static void ftlToHtml(Object data, String htmlName, String basePackagePath, String templateName) throws Exception {
        if (StringUtils.isEmpty(htmlName)) {
            throw new RuntimeException("htmlName is null");
        }
        if (StringUtils.isEmpty(templateName)) {
            throw new RuntimeException("templateName is null");
        }
        FileOutputStream fileOut = new FileOutputStream(htmlName);
        Configuration cfg = getConfig(basePackagePath);
        Template template = cfg.getTemplate(templateName);
        OutputStreamWriter out = new OutputStreamWriter(fileOut, StandardCharsets.UTF_8);
        template.process(data, out);
        out.close();
        fileOut.flush();
        fileOut.close();
    }

    /**
     * 将模板与数据合并
     * @param data 需要合并的数据，可为空(需要在ftl中写好)
     * @param basePackagePath 存放模板的路径，只加载resources下的pdf目录，如需要其他方式请自定义修改
     *                        e.g. /templates/pdf
     * @param templateName 所需要生成html的ftl文件模板，需包含后缀名
     * @return 含数据的html字符串
     * @throws Exception 异常
     */
    public static String freeMarkerToString(Object data, String basePackagePath, String templateName) throws Exception {
        if (StringUtils.isEmpty(templateName)) {
            throw new RuntimeException("templateName is null");
        }
        // 获取模板
        Configuration cfg = getConfig(basePackagePath);
        Template template = cfg.getTemplate(templateName);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
    }

    /**
     * 获取freemarker模板配置对象
     * @param basePackagePath 模板路径，只加载resources下的pdf目录，如需要其他方式请自定义修改
     *                        e.g. /templates/pdf
     * @return 模板配置对象
     */
    private static Configuration getConfig(String basePackagePath) {
        if (StringUtils.isEmpty(basePackagePath)){
            throw new RuntimeException("basePackagePath is null");
        }
        Configuration cfg = new Configuration(Configuration.getVersion());
        cfg.setDefaultEncoding("UTF-8");
        // 只加载resources下的pdf目录，如需要其他方式请自定义修改
        cfg.setClassForTemplateLoading(FtlUtil.class, basePackagePath);
        return cfg;
    }
}
