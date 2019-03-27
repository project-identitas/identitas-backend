package com.identitas.dao;

import com.identitas.model.Buzz;
import com.identitas.model.Poll;
import com.identitas.requestBody.BuzzRequestBody;
import org.hibernate.query.Query;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

/**
 * Created by toshikijahja on 6/7/17.
 */
public class PollDao extends BaseDao<Poll> {

    public PollDao(final SessionProvider sessionProvider) {
        super(sessionProvider, Poll.class);
    }

    public List<Poll> savePolls(final BuzzRequestBody buzzRequestBody,
                                final Buzz buzz) {
        requireNonNull(buzzRequestBody);
        requireNonNull(buzz);
        getSessionProvider().startTransaction();
        final List<Poll> polls = buzzRequestBody.getPolls().stream()
            .map(text -> {
                final Poll poll = new Poll.Builder().buzz(buzz).text(text).count(0).build();
                getSessionProvider().getSession().persist(poll);
                return poll;
            }).collect(toList());
        getSessionProvider().commitTransaction();
        return polls;
    }

    public void increasePollCount(final int pollId) {
        final Query query = getSessionProvider().getSession().createQuery(
                "UPDATE " + clazz.getName() + " p SET p.count = p.count + 1 WHERE p.id = :pollId");
        query.setParameter("pollId", pollId);
        query.executeUpdate();
    }
}
