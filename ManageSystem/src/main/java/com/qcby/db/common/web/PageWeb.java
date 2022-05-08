package com.qcby.db.common.web;

//import com.github.pagehelper.Page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @className: PageWeb
 * @description: 分页信息
 * @author: lxt
 * @create: 2021-08-27 21:49
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageWeb<T> {


    private long pageNo;
    private long pageSize;
    private long total;
    private long totalPages;
    private List<T> records;


//    public PageWeb(Page<T> page) {
//        this.pageNo = page.getPageNum();
//        this.pageSize = page.getPageSize();
//        this.total = page.getTotal();
//        this.totalPages = page.getPages();
//        this.records = page.getResult();
//    }


    public PageWeb(IPage<T> page) {
        this.pageNo = page.getCurrent();
        this.pageSize = page.getSize();
        this.total = page.getTotal();
        this.totalPages = page.getPages();
        this.records = page.getRecords();
    }

    /**
     * 返回分页信息
     *
     * @param page
     * @return
     */
    public static <T> PageWeb build(IPage<T> page) {
        return new PageWeb<>(page);
    }

    public static void main(String[] args) {

        PageWeb pageWeb = new PageWeb();

        pageWeb.setPageNo(1).setPageSize(5);

        pageWeb.setTotal(2).setTotalPages(6777);

        System.out.println(pageWeb.getPageNo());

        System.out.println(pageWeb.getTotal());

        System.out.println(pageWeb);
    }
}
