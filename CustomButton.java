package views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by daminduweerasooriya on 10/24/16.
 */

public class CustomButton extends android.support.v7.widget.AppCompatButton {

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomButton(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            //Log.d("Assets",getContext().getAssets().toString());
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "minionpro_regular.ttf");
            setTypeface(tf);
        }
    }
}
