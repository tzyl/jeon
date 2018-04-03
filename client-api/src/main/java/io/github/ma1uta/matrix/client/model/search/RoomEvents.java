package io.github.ma1uta.matrix.client.model.search;

import io.github.ma1uta.matrix.client.model.filter.Filter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomEvents {

    interface Content {
        String CONTENT_BODY = "content.body";
        String CONTENT_NAME = "content.name";
        String CONTENT_TOPIC = "content.topic";
    }

    interface Order {
        String RECENT = "recent";
        String rank = "rank";
    }

    private String searchTerm;
    private List<String> keys;
    private Filter filter;
    private String orderBy;
    private EventContextRequest eventContext;
    private Boolean includeState;
    private Groupings groupings;
}