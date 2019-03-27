package com.identitas.requestBody;

import java.io.Serializable;

/**
 * Created by toshikijahja on 3/26/19.
 */
public class CommentLikeRequestBody implements Serializable {

    private int commentId;
    private boolean liked;

    public CommentLikeRequestBody() {

    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(final int commentId) {
        this.commentId = commentId;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(final boolean liked) {
        this.liked = liked;
    }
}
