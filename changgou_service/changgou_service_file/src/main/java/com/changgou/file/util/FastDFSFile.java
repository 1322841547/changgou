package com.changgou.file.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ccwu
 * @date 2021/10/23 16:23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastDFSFile {

    private String name;  //文件名
    private byte[] content; //文件内容
    private String ext;  //文件扩展名
    private String md5;  //文件MD5摘要值
    private String author; //文件作者

    public FastDFSFile(String name, byte[] content, String ext) {
        this.name = name;
        this.content = content;
        this.ext = ext;
    }
}
