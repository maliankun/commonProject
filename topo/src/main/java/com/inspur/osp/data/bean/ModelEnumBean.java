package com.inspur.osp.data.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by liuchen_ on 2018/4/19.
 */

public class ModelEnumBean {
    private List<Map<String, String>> displayValue;
    private List<Map<String, Object>> rawValue;

    private List<String> source;
    private List<String> sourceName;
    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public static class Page {
        private int number;
        private int size;
        private int totalElements;
        private int totalPages;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
    }

    public static class DisplayValueBean {
        private String UUID;
        private String name;
        private String resType;

        public DisplayValueBean(String UUID, String name) {
            this.UUID = UUID;
            this.name = name;
        }

        public DisplayValueBean(String UUID, String name, String resType) {
            this.UUID = UUID;
            this.name = name;
            this.resType=resType;
        }

        public String getUUID() {
            return UUID;
        }

        public void setUUID(String UUID) {
            this.UUID = UUID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getResType() {
            return resType;
        }

        public void setResType(String resType) {
            this.resType = resType;
        }
    }

    public List<Map<String, String>> getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(List<Map<String, String>> displayValue) {
        this.displayValue = displayValue;
    }

    public List<Map<String, Object>> getRawValue() {
        return rawValue;
    }

    public void setRawValue(List<Map<String, Object>> rawValue) {
        this.rawValue = rawValue;
    }

    public List<String> getSource() {
        return source;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }

    public List<String> getSourceName() {
        return sourceName;
    }

    public void setSourceName(List<String> sourceName) {
        this.sourceName = sourceName;
    }
}
