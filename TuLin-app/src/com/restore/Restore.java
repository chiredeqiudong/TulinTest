package com.restore;

import com.io.Input;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zy293
 * 操作记录
 * 时间范围
 */
public class Restore {

    /**
     * 时间存储集合
     */
    public static List<String>  timeList = new ArrayList<>();

    /**
     * 时间格式化器
     */
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");


    public static void addInfo(LocalDateTime actionLogging,String info){
        //解析时间
        String time = FORMATTER.format(actionLogging);
        timeList.add("在" + time + ",添加了" + info + "信息");
        //保存数据
        Input.timeList(timeList);
    }

    public static void polishInfo(LocalDateTime actionLogging,String info){
        //解析时间
        String time = FORMATTER.format(actionLogging);
        timeList.add("在" + time + ",修改了" + info + "信息");
        //保存数据
        Input.timeList(timeList);
    }


    public static void deleteInfo(LocalDateTime actionLogging,String info){
        //解析时间
        String time = FORMATTER.format(actionLogging);
        timeList.add("在" + time + ",删除了" + info + "信息");
        //保存数据
        Input.timeList(timeList);
    }


    public static void showInfo(){
        // 特判
        if (timeList.isEmpty()){
            System.out.println("还没有任何操作记录！");
            return;
        }
        System.out.println("\n\t\t*****************操作信息记录****************");
        int count = 0;
        for (String time : timeList) {
            count++;
            System.out.println(time);
            System.out.println("-------------------------------");
        }
        System.out.println("总计:" + count + "条操作记录");

    }



}
