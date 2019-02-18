package com.udd.naucnacentrala.web.dto;

import java.io.Serializable;

public class FormSubmissionDto  implements Serializable {
    String fieldId;
    String fieldValue;


    public FormSubmissionDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public FormSubmissionDto(String fieldId, String fieldValue) {
        super();
        this.fieldId = fieldId;
        this.fieldValue = fieldValue;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}
