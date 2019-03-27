package com.identitas.source.user;

import com.identitas.auth.UserAuth;
import com.identitas.dao.FeedbackDao;
import com.identitas.dao.SessionProvider;
import com.identitas.dao.UserDao;
import com.identitas.model.Feedback;
import com.identitas.model.User;
import com.identitas.requestBody.FeedbackRequestBody;
import com.identitas.view.FeedbackView;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.Optional;

@Path("/user")
@UserAuth
public class FeedbackSource {

    @POST
    @Path("/feedback")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public FeedbackView submitFeedback(final FeedbackRequestBody feedbackRequestBody,
                                       @Context final SecurityContext securityContext) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final UserDao userDao = new UserDao(sessionProvider);
            final FeedbackDao feedbackDao = new FeedbackDao(sessionProvider);

            final Optional<User> user = userDao.getByGuid(securityContext.getUserPrincipal().getName());

            sessionProvider.startTransaction();
            final Feedback feedback = feedbackDao.submitFeedback(feedbackRequestBody, user.get().getId());
            sessionProvider.commitTransaction();

            return new FeedbackView(feedback);
        }
    }
}