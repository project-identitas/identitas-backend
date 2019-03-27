package com.identitas.dao;

import com.identitas.model.ReportCategory;

/**
 * Created by toshikijahja on 11/7/18.
 */
public class ReportCategoryDao extends BaseDao<ReportCategory> {

    public ReportCategoryDao(final SessionProvider sessionProvider) {
        super(sessionProvider, ReportCategory.class);
    }
}
