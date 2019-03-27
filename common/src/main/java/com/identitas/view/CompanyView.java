package com.identitas.view;

import com.identitas.model.Company;


/**
 * Created by toshikijahja on 3/26/19.
 */
public class CompanyView extends BaseView {

    private final Company company;

    public CompanyView(final Company company) {
        this.company = company;
    }

    public String getName() {
        return company.getName();
    }

    public int getId() {
        return company.getId();
    }

}
