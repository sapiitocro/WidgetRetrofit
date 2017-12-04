package net.simplifiedlearning.retrofitexample;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Gian Franco on 4/12/2017.
 */

public class WidgetService extends RemoteViewsService{

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetAdapter(this);
    }
}
