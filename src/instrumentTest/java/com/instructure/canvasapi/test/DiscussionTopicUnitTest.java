package com.instructure.canvasapi.test;

import android.test.InstrumentationTestCase;
import com.google.gson.Gson;
import com.instructure.canvasapi.model.DiscussionEntry;
import com.instructure.canvasapi.model.DiscussionParticipant;
import com.instructure.canvasapi.model.DiscussionTopic;
import com.instructure.canvasapi.utilities.CanvasRestAdapter;

/**
 * Created by Josh Ruesch on 9/18/13.
 *
 * Copyright (c) 2014 Instructure. All rights reserved.
 */
public class DiscussionTopicUnitTest extends InstrumentationTestCase {

   public void testDiscussionTopic() {
        String discussionTopicJSON = "{ \"unread_entries\": [1,2,3], \"forced_entries\": [], \"participants\": [{\"id\":3828648,\"display_name\":\"Drip Derskey\",\"avatar_image_url\":\"https://mobiledev.instructure.com/images/thumbnails/32957548/krblSV5HHvhqqlxUCtvAsR6AkGMI21qsw8i2y1Tx\",\"html_url\":\"https://mobiledev.instructure.com/courses/24219/users/3828648\"},{\"id\":3363291,\"display_name\":\"Josher\",\"avatar_image_url\":\"https://mobiledev.instructure.com/images/thumbnails/25871866/VCYN4XMwkJjyXJQy2tyKXibUdPnIT4aAmZCPstGP\",\"html_url\":\"https://mobiledev.instructure.com/courses/24219/users/3363291\"}], \"view\": [{\"created_at\":\"2013-05-29T15:50:24Z\",\"id\":5203752,\"parent_id\":null,\"updated_at\":\"2013-05-29T15:50:24Z\",\"user_id\":3828648,\"message\":\"Clojure1!!11!!\"},{\"created_at\":\"2013-05-29T15:51:18Z\",\"id\":5203767,\"parent_id\":null,\"updated_at\":\"2013-05-29T15:51:18Z\",\"user_id\":3828648,\"message\":\"I mean: Clojure is the best programming language.\",\"replies\":[{\"created_at\":\"2013-10-07T18:06:58Z\",\"id\":7221310,\"parent_id\":5203767,\"updated_at\":\"2013-10-07T18:06:58Z\",\"user_id\":3363291,\"message\":\"False\"}]}], \"new_entries\": [] }";
        Gson gson = CanvasRestAdapter.getGSONParser();
        DiscussionTopic discussionTopic = gson.fromJson(discussionTopicJSON,DiscussionTopic.class);

        assertNotNull(discussionTopic);

        assertNotNull(discussionTopic.getViews());

        for(DiscussionEntry discussionEntry : discussionTopic.getViews())
        {
            DiscussionEntryUnitTest.testDiscussionEntryView(discussionEntry);
        }

        assertNotNull(discussionTopic.getUnreadEntries());

        assertTrue(discussionTopic.getUnreadEntries().length > 0);

        assertNotNull(discussionTopic.getParticipants());

        for(DiscussionParticipant discussionParticipant : discussionTopic.getParticipants()){
            assertNotNull(discussionParticipant);

            assertNotNull(discussionParticipant.getHtmlUrl());

            assertNotNull(discussionParticipant.getDisplayName());

            assertNotNull(discussionParticipant.getAvatarUrl());

            assertTrue(discussionParticipant.getId() > 0);
        }
    }
}
