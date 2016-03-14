package com.example.user.drawingapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.user.drawingapp.R;

/**
 * Created by User on 14/03/2016.
 */
public class PainterView extends View{

    //region Instance Variables
    private Paint paint;

    private Path path;

    private Drawable shape;

    private float lastX;

    private float lastY;
    //endregion
    //region Constructors
    public PainterView(Context context) {
        super(context);
        initFields(context);
    }

    private void initFields(Context context) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(15);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        shape = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.paint_background);
        path = new Path();
    }

    public PainterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFields(context);
    }

    public PainterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFields(context);
    }


    //endregion


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        lastX = event.getX();
        lastY = event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(lastX,lastY);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(lastX,lastY);
                break;
            case MotionEvent.ACTION_UP:
                path.lineTo(lastX,lastY);
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        shape.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        shape.draw(canvas);
        canvas.drawPath(path,paint);

    }

    public void clear(){
        path.reset();
        invalidate();
    }
}
