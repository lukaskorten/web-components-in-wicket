package de.korten.tasks.base;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.GenericWebMarkupContainer;
import org.apache.wicket.model.IModel;

import java.time.LocalDateTime;

public class TimeGap extends GenericWebMarkupContainer<LocalDateTime> {

    public TimeGap(String id, IModel<LocalDateTime> model) {
        super(id, model);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        tag.setName("time-gap");
        tag.put("from", getModelObject().toString());
        tag.put("locale", getLocale().getCountry());
    }
}
