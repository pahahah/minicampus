package com.example.hellowebsite.admin.model;

import lombok.Data;

@Data
public class MemberParam {
    String searchType;
    String searchValue;
    String userId;

    long pageIndex;
    long pageSize;

    public long getPageStart(){
        init();
        return (pageIndex - 1) * pageSize;
    }

    public long getPageEnd(){
        init();
        return pageSize;
    }
    public void init(){
        if (pageIndex < 1){
            pageIndex = 1;
        }
        if (pageSize < 10){
            pageSize = 10;
        }
    }

}
