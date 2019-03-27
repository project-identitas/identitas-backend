package com.identitas.view;

import java.util.List;

/**
 * Created by toshikijahja on 3/26/19.
 */
public class BuzzCommentListView extends BaseView {

    private final BuzzView buzzView;
    private final List<CommentView> commentViews;

    public BuzzCommentListView(final BuzzView buzzView,
                               final List<CommentView> commentViews) {
        this.buzzView = buzzView;
        this.commentViews = commentViews;
    }

    public BuzzView getBuzz() {
        return buzzView;
    }

    public List<CommentView> getCommentList() {
        return commentViews;
    }
}
