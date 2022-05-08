package com.craffic.convey.jobserver.utils;

import java.util.List;
import java.util.Random;

/**
 * 数字随机生成器
 */
public class RandomNumUtil {

    /**
     * 从集合里随机生成一个数字
     * 传进来的list必须要先判断非空
     * @param list
     * @return
     */
    public static Integer randomNumFormList(List list){
        int size = list.size();
        Random random = new Random();
        return random.nextInt(size);
    }

    public static int randomRangeNumber(int number) {
        Random random = new Random();
        return random.nextInt(number);
    }
}
