package com.sixbynine.set.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.sixbynine.set.object.Card;

/**
 * Created by steviekideckel on 10/25/14.
 */
public class SetCardView extends View {

    private Card mCard;

    public SetCardView(Context context) {
        super(context);
    }

    public SetCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
