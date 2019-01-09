package com.rocky.hellonetty.server.time;

import java.util.Date;

/**
 * Created by Rocky on 2019-01-02.
 * 自定义时间数据类
 */
public class Time {

	private final long value;

    public Time() {
        // 除以1000是为了使时间精确到秒
        this(System.currentTimeMillis() / 1000L);
    }

    public Time(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((value()) * 1000L).toString();
    }

}
