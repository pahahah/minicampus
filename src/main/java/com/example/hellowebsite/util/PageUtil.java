package com.example.hellowebsite.util;

public class PageUtil {
    /**
     * total number
     */
    private long totalCount;

    /**
     * total number for one page
     */
    private long pageSize = 10;


    /**
     * block number of total pages
     */
    private long totalBlockCount;

    /**
     * page block number
     */
    private long pageBlockSize = 10;

    /**
     * start number (index) of page
     */
    private long startPage;

    /**
     *  end number(index) of page
     */
    private long endPage;


    /**
     * current page index
     */
    private long pageIndex;

    /**
     * parameter(query string) when moving a page
     */
    private String queryString;




    public PageUtil(long totalCount, long pageIndex, String queryString) {
        this.totalCount = totalCount;
        this.pageIndex = pageIndex;
        this.queryString = queryString;


    }

    public PageUtil(long totalCount, long pageIndex, String queryString, long pageSize, long pageBlockSize) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.queryString =queryString;
        this.pageBlockSize = pageBlockSize;

    }

    public String pager() {
        if (pageIndex < 1){
            pageIndex = 1;
        }

        if (pageSize < 1){
            pageSize = 1;
        }
        totalBlockCount = (long)Math.ceil((double)totalCount / pageSize);
        startPage = ((pageIndex - 1) / pageBlockSize) * pageBlockSize + 1;

        endPage = startPage + pageBlockSize - 1;
        if(endPage > totalBlockCount){
            endPage = totalBlockCount;
        }


        StringBuilder sb = new StringBuilder();

        long previousPageIndex = startPage > 1 ? startPage - 1 : 1;
        long nextPageIndex = endPage < totalBlockCount ? endPage + 1 : totalBlockCount;

        String addQueryString = "";
        if (queryString != null && queryString.length() > 0){
            addQueryString = "&" + queryString;
        }

        sb.append(String.format("<a href='?pageIndex=%d%s'>&lt;&lt;</a>", 1, addQueryString));
        sb.append(System.lineSeparator());
        sb.append(String.format("<a href='?pageIndex=%d%s'>&lt;</a>", previousPageIndex, addQueryString));
        sb.append(System.lineSeparator());

        for(long i = startPage; i<= endPage; i++) {
            if (i == pageIndex) {
                sb.append(String.format("<a class='on' href='?pageIndex=%d%s'>%d</a>", i, addQueryString, i));
            } else {
                sb.append(String.format("<a href='?pageIndex=%d%s'>%d</a>", i, addQueryString, i));
            }
            sb.append(System.lineSeparator());
        }

        sb.append(String.format("<a href='?pageIndex=%d%s'>&gt;</a>", nextPageIndex, addQueryString));
        sb.append(System.lineSeparator());
        sb.append(String.format("<a href='?pageIndex=%d%s'>&gt;&gt;</a>", totalBlockCount, addQueryString));
        sb.append(System.lineSeparator());

        return sb.toString();

    }

}
