package com.changgou;

import com.changgou.util.IdWorker;

/**
 * @author ccwu
 * @date 2021/10/25 19:32
 */
public class Test {
    public static void main(String[] args) {
        IdWorker idWorker = new IdWorker(12,14);
        for(int i=0;i<100;i++){
            long id = idWorker.nextId();
            System.out.println(id); }
    }
}