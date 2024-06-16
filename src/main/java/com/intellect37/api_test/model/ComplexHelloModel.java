package com.intellect37.api_test.model;

import java.util.List;

public class ComplexHelloModel {
    private String message;
    private List<String> details;
    private InnerModel innerModel;

    public ComplexHelloModel() {}

    public ComplexHelloModel(String message, List<String> details, InnerModel innerModel) {
        this.message = message;
        this.details = details;
        this.innerModel = innerModel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public InnerModel getInnerModel() {
        return innerModel;
    }

    public void setInnerModel(InnerModel innerModel) {
        this.innerModel = innerModel;
    }

    public static class InnerModel {
        private String name;
        private int value;

        public InnerModel() {}

        public InnerModel(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
