package com.example.learnandroid.learnrecyclerview;

import java.util.ArrayList;
import java.util.List;

public class SlideCardBean {
    private int postition;
    private String url;
    private String name;

    public SlideCardBean(int postition, String url, String name) {
        this.postition = postition;
        this.url = url;
        this.name = name;
    }

    public int getPostition() {
        return postition;
    }

    public SlideCardBean setPostition(int postition) {
        this.postition = postition;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public SlideCardBean setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getName() {
        return name;
    }

    public SlideCardBean setName(String name) {
        this.name = name;
        return this;
    }

    public static List<SlideCardBean> initDatas() {
        List<SlideCardBean> datas = new ArrayList<>();
        for (int i = 0 ;i < 100; i ++) {
            datas.add(new SlideCardBean(i, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595434963213&di=5d07d9de35f42c16238c3076119a6e98&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fmobile%2F2018-12-13%2F5c120783eba2b.jpg", "美女" + i));
        }
        return datas;
    }
}
