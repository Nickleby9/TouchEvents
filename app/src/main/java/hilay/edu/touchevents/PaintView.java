package hilay.edu.touchevents;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Android2017 on 8/15/2017.
 */

public class PaintView extends View {
    public PaintView(Context context) {
        this(context, null);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    Paint mPaint;
    Path mPath;

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(18);
        mPaint.setAntiAlias(true);
//        mPaint.setDither(true);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
//        canvas.drawARGB(255, 255, 255, 255);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                mPath.addCircle(x, y, 1, Path.Direction.CW);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:

                int history = event.getHistorySize();
                for (int i = 0; i < history; i++) {
                    mPath.lineTo(event.getHistoricalX(i), event.getHistoricalY(i));
                }
                mPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        invalidate();

        return true;
    }

    void clear(){
        mPath.reset();
    }
}
