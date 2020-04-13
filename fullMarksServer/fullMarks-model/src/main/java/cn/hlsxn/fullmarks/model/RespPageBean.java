package cn.hlsxn.fullmarks.model;

import java.util.List;

public class RespPageBean {
    private Long total;
    private List<?> data;
    private Long countPage;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Long getCountPage() {
        return countPage;
    }

    public void setCountPage(Long countPage) {
        this.countPage = countPage;
    }
}
