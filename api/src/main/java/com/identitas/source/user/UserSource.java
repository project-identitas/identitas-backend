package com.identitas.source.user;

import com.identitas.BaseResponse;
import com.identitas.auth.UserAuth;
import com.identitas.dao.AuthenticationDao;
import com.identitas.dao.CompanyDao;
import com.identitas.dao.CompanyEmailDao;
import com.identitas.dao.SessionProvider;
import com.identitas.dao.UserDao;
import com.identitas.dao.UserEmailDao;
import com.identitas.exception.BuzzException;
import com.identitas.model.Authentication;
import com.identitas.model.Company;
import com.identitas.model.CompanyEmail;
import com.identitas.model.User;
import com.identitas.model.UserEmail;
import com.identitas.requestBody.UserRequestBody;
import com.identitas.view.UserEmailListView;
import com.identitas.view.UserView;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.identitas.exception.BadRequest.ALIAS_EXIST;
import static com.identitas.exception.BadRequest.COMPANY_NOT_EXIST;
import static com.identitas.exception.BadRequest.USER_ALREADY_EXIST;
import static com.identitas.exception.BadRequest.USER_NOT_VERIFIED;
import static com.identitas.model.Authentication.Status.UNVERIFIED;
import static java.util.Objects.requireNonNull;

@Path("/user")
@UserAuth
public class UserSource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getUser(@Context final SecurityContext securityContext) {
        System.out.println("ITS WORKING!!!");
        return new BaseResponse();
//        try (final SessionProvider sessionProvider = new SessionProvider()) {
//            final UserDao userDao = new UserDao(sessionProvider);
//            final User user = userDao.getByGuid(securityContext.getUserPrincipal().getName()).get();
//            return new UserView(user);
//        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public UserView createUser(@Context final SecurityContext securityContext) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final UserDao userDao = new UserDao(sessionProvider);
            final UserEmailDao userEmailDao = new UserEmailDao(sessionProvider);
            final CompanyEmailDao companyEmailDao = new CompanyEmailDao(sessionProvider);
            final CompanyDao companyDao = new CompanyDao(sessionProvider);
            final AuthenticationDao authenticationDao = new AuthenticationDao(sessionProvider);

            final String guid = securityContext.getUserPrincipal().getName();

            final Optional<User> userExistCheck = userDao.getByGuid(guid);
            if (userExistCheck.isPresent()) {
                throw new BuzzException(USER_ALREADY_EXIST);
            }

            final Optional<Authentication> authentication = authenticationDao.getByGuid(guid);
            if (!authentication.isPresent() || authentication.get().getStatus() == UNVERIFIED) {
                throw new BuzzException(USER_NOT_VERIFIED);
            }

            final Optional<CompanyEmail> companyEmail = companyEmailDao.getByEmail(authentication.get().getEmail());
            if (!companyEmail.isPresent()) {
                throw new BuzzException(COMPANY_NOT_EXIST);
            }
            final Company company = companyDao.getById(companyEmail.get().getCompanyId());

            sessionProvider.startTransaction();
            final User user = userDao.createUser(guid);
            userEmailDao.createUserEmail(authentication.get().getEmail(), companyDao.getEveryone(), user);
            userEmailDao.createUserEmail(authentication.get().getEmail(), company, user);
            sessionProvider.commitTransaction();

            return new UserView(user);
        }
    }

    @GET
    @Path("/email")
    @Produces(MediaType.APPLICATION_JSON)
    public UserEmailListView getUserEmail(@Context final SecurityContext securityContext) {
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final UserEmailDao userEmailDao = new UserEmailDao(sessionProvider);
            final UserDao userDao = new UserDao(sessionProvider);
            final User user = userDao.getByGuid(securityContext.getUserPrincipal().getName()).get();
            final List<UserEmail> userEmails = userEmailDao.getByUserId(user.getId());

            return new UserEmailListView(userEmails, user);
        }
    }

    @POST
    @Path("/alias")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserView setAlias(final UserRequestBody userRequestBody,
                             @Context final SecurityContext securityContext) {
        requireNonNull(userRequestBody.getAlias());
        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final UserDao userDao = new UserDao(sessionProvider);
            final Optional<User> user = userDao.getByGuid(securityContext.getUserPrincipal().getName());
            if (Objects.equals(userRequestBody.getAlias(), user.get().getAlias())) {
                return new UserView(user.get());
            }

            // check if alias has already being used
            final Optional<User> userOptional = userDao.getByAlias(userRequestBody.getAlias());
            if (userOptional.isPresent()) {
                throw new BuzzException(ALIAS_EXIST);
            }

            sessionProvider.startTransaction();
            userDao.setAlias(userRequestBody.getAlias(), user.get().getId());
            sessionProvider.commitTransaction();
        }

        try (final SessionProvider sessionProvider = new SessionProvider()) {
            final UserDao userDao = new UserDao(sessionProvider);
            final Optional<User> updatedUser = userDao.getByGuid(securityContext.getUserPrincipal().getName());
            return new UserView(updatedUser.get());
        }
    }
}