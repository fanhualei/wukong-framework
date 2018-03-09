package com.wukong.core.result;

import java.util.Collection;

public class PageResult<T extends Collection> extends ResultSupport {

    protected T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private Meta meta;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public int getPageNo() {
        return meta.getPageNo();
    }

    public void setPageNo(int pageNo) {
        this.meta.setPageNo(pageNo);
    }

    public int getPageSize() {
        return this.meta.getPageSize();
    }

    public void setPageSize(int pageSize) {
        this.meta.setPageSize(pageSize);
    }

    public int getTotalCount() {
        return this.meta.getTotalCount();
    }

    public void setTotalCount(int totalCount) {
        this.meta.setTotalCount(totalCount);
    }

    public PageResult() {
        this.meta = new Meta();
    }

    public PageResult(int pageNo, int pageSize, int totalCount, T data) {
        this();
        this.setPageNo(pageNo);
        this.setPageSize(pageSize);
        this.setTotalCount(totalCount);
        this.setData(data);
    }

    /**
     * 接口调用失败,有错误字符串码和描述,有返回对象
     *
     * @param code
     * @param message
     * @param data
     * @param <U>
     * @return
     */
    public static <U extends Collection> PageResult<U> newFailResult(int code, String message, U data) {
        PageResult<U> pageResult = new PageResult<U>();
        pageResult.setCode(code);
        pageResult.setMessage(message);
        pageResult.setData(data);
        return pageResult;
    }

    /**
     * 接口调用失败,有错误字符串码和描述,没有返回对象
     *
     * @param code
     * @param message
     * @param <U>
     * @return
     */
    public static <U extends Collection> PageResult<U> newFailResult(int code, String message) {
        PageResult<U> pageResult = new PageResult<U>();
        pageResult.setCode(code);
        pageResult.setMessage(message);
        pageResult.setMeta(null);
        return pageResult;
    }

    public static <U extends Collection> PageResult<U> newSuccessResult(int pageNo, int pageSize, int totalCount, U data) {
        return new PageResult<U>(pageNo, pageSize, totalCount, data);
    }

    private class Meta {
        protected int pageNo = 1;
        protected int pageSize;
        protected int totalCount;

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            if (pageNo <= 0) {
                pageNo = 1;
            }
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            if (pageSize < 0) {
                pageSize = 0;
            }
            this.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }
    }
}

