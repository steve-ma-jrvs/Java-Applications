package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceImpTest {

    @InjectMocks
    private TwitterServiceImp service;
    @Mock
    private CrdDao mockDao;

    @Test
    public void postTweet() {

        Tweet mockTweet = new Tweet();
        mockTweet.setText("This is a fake tweet3");
        when(mockDao.create(mockTweet.getClass())).thenReturn(mockTweet);


        service.postTweet("some tweet3", 0.0, 0.0);

        try {
            service.postTweet("", 0.0, 0.0);
            fail();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}