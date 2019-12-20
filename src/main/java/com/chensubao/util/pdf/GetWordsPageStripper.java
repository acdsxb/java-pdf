package com.chensubao.util.pdf;

import com.chensubao.util.JacksonUtil;
import org.apache.pdfbox.text.TextPosition;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @link #{https://segmentfault.com/a/1190000018700557}
 */
public class GetWordsPageStripper extends PDFTextStripper {
    private List<String> words;
    private Map<String, List<Integer>> map;
    private int skipPage;
    private int min;
    private int max;

    public GetWordsPageStripper(List<String> words, int skipPage) throws IOException {
        super();
        this.words = words;
        this.skipPage = skipPage;
        this.min = 2;
        this.max = 15;
        map = new HashMap<>(16);
    }

    public GetWordsPageStripper(List<String> words, int skipPage, int min, int max) throws IOException {
        super();
        this.words = words;
        this.skipPage = skipPage;
        this.min = min;
        this.max = max;
        map = new HashMap<>(16);
    }

    public Map<String, List<Integer>> map() {
        return map;
    }

    @Override
    protected void writeString(String text, List<TextPosition> textPositions) throws IOException{
        if (getCurrentPageNo() > skipPage && text.length() >= min && text.length() <= max) {
            for (String word : words) {
                if (text.equals(word)) {
                    List<Integer> integers;
                    if (map.get(word) == null) {
                        integers = new ArrayList<>();
                    } else {
                        integers = JacksonUtil.toList(JacksonUtil.toString(map.get(word)), Integer.class);
                    }
                    if (null == integers) {
                        throw new IllegalArgumentException("integers为空");
                    }
                    integers.add(getCurrentPageNo());
                    map.put(word, integers);
                }
            }
        }
    }
}
