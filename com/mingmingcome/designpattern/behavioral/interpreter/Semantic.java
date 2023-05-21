package com.mingmingcome.designpattern.behavioral.interpreter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @when 2023/4/30 2:49 PM
 * @who luhaoming
 * @what Semantic 语义分析
 **/
public class Semantic {

    public void analyze() throws IOException {
        String input = readFile();
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);
        parser.parse();
        // 调用绘图工具类的draw方法画点
        DrawUtils.draw();
    }

    public String readFile() throws IOException {
        // 读取一个graphic.txt的文件
        File file = new File("/Users/luhaoming/Documents/GitRepo/cnblogs/com/mingmingcome/designpattern/behavioral/interpreter/heart.txt");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        // 读取文件的内容,将文件内容转换为字符串
        byte[] bytes = new byte[(int) file.length()];
        bis.read(bytes);
        return new String(bytes);
    }

}
