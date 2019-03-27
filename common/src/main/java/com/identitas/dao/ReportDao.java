package com.identitas.dao;

import com.identitas.model.Report;
import com.identitas.model.ReportCategory;
import com.identitas.model.User;
import com.identitas.requestBody.ReportRequestBody;

import static java.util.Objects.requireNonNull;

/**
 * Created by toshikijahja on 11/7/18.
 */
public class ReportDao extends BaseDao<Report> {

    public ReportDao(final SessionProvider sessionProvider) {
        super(sessionProvider, Report.class);
    }

    public Report submitReport(final ReportRequestBody reportRequestBody,
                               final ReportCategory reportCategory,
                               final User user) {
        requireNonNull(reportRequestBody);
        requireNonNull(reportCategory);
        requireNonNull(user);
        getSessionProvider().startTransaction();
        final Report report = new Report.Builder()
                .reportCategory(reportCategory)
                .user(user)
                .itemId(reportRequestBody.getItemId())
                .type(reportRequestBody.getType())
                .comments(reportRequestBody.getComments())
                .build();
        getSessionProvider().getSession().persist(report);
        getSessionProvider().commitTransaction();
        return report;
    }
}
