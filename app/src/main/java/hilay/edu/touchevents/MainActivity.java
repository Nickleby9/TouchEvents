package hilay.edu.touchevents;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "ness";
    private GestureDetectorCompat detector = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PaintView(this));


        detector = new GestureDetectorCompat(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float x1 = e1.getX();
                float x2 = e2.getX();
                float y1 = e1.getY();
                float y2 = e2.getY();

                float dx = x2 - x1;
                float dy = y2 - y1;


                if (Math.abs(dx) > Math.abs(dy)){
                    //HORIZONTAL
                    if (x2 > x1){
                        Log.d(TAG, "onFling: RIGHT");

                    } else {
                        Log.d(TAG, "onFling: LEFT");
                    }
                } else {
                    //VERTICAL
                    if (y2 > y1){
                        Log.d(TAG, "onFling: DOWN");
                    } else {
                        Log.d(TAG, "onFling: UP");
                    }
                }


                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        detector.onTouchEvent(event);

        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                Log.d(TAG, "onTouchEvent: DOWN");
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.d(TAG, "onTouchEvent: UP");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.d(TAG, "onTouchEvent: MOVE");
//                break;
//        }
        return super.onTouchEvent(event);
    }
}
