package com.identitas.view;

import com.identitas.model.ReportCategory;

/**
 * Created by toshikijahja on 3/26/19.
 */
public class ReportCategoryView extends BaseView {

    private final ReportCategory reportCategory;

    public ReportCategoryView(final ReportCategory reportCategory) {
        this.reportCategory = reportCategory;
    }

    public int getId() {
        return reportCategory.getId();
    }

    public String getCategory() {
        return reportCategory.getCategory();
    }
}
