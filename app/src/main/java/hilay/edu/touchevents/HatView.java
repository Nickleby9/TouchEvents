package hilay.edu.touchevents;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class HatView extends View {

    private static final String TAG = "ness";
    Paint p = new Paint();
    Bitmap hat;
    Bitmap face;
    float hatLeftPosition = 300;
    float hatTopPosition = 500;
    int hatX;
    int hatY;
    private boolean mMovingHat;

    public HatView(Context context) {
        this(context, null);
    }

    public HatView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.STROKE);
        p.setDither(true);
        p.setAntiAlias(true);
        p.setStrokeWidth(20);
        p.setStrokeCap(Paint.Cap.ROUND);

        hat = BitmapFactory.decodeResource(getResources(), R.drawable.cylinder);
        face = BitmapFactory.decodeResource(getResources(), R.drawable.head);

        hatY = hat.getHeight();
        hatX = hat.getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(face, 100, 300, null);
        canvas.drawBitmap(hat, hatLeftPosition, hatTopPosition, null);

        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() >= hatLeftPosition && event.getX() <= hatLeftPosition + hatX) {
                    if (event.getY() >= hatTopPosition && event.getY() <= hatTopPosition + hatY) {
                        mMovingHat = true;
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            case MotionEvent.ACTION_MOVE:
                if (mMovingHat) {
                    hatLeftPosition = event.getX() - hatX / 2;
                    hatTopPosition = event.getY() - hatY / 2;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                mMovingHat = false;
                break;
        }

        return super.onTouchEvent(event);
    }
}
