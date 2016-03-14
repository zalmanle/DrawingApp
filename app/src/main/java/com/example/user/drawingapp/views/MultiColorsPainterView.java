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

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by User on 14/03/2016.
 */
public class MultiColorsPainterView extends View {

    //region Constants
    private static final int DEFAULT_COLOR = Color.BLUE;

    private static final int DEFAULT_SIZE = 5; //1 - 33
    //endregion
    //region Instance Variables
    private Drawable shape;

    private List<Integer>boxIds;

    private PainterBox currentBox;

    private List<PainterBox>painterBoxes;

    private int color;

    private int weight;

    private Path path;
    //endregion
    //region Constructors
    public MultiColorsPainterView(Context context) {
        super(context);
        initFields(context);
    }

    private void initPainterBox(){

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(weight);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        path = new Path();
        PainterBox box = new PainterBox(paint,path);
        painterBoxes.add(box);
        boxIds.add(box.id);
        currentBox = box;


    }
    private void initFields(Context context) {
        boxIds = new LinkedList<Integer>();
        painterBoxes = new LinkedList<PainterBox>();
        shape = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.paint_background);
        color = DEFAULT_COLOR;
        weight = DEFAULT_SIZE;
    }

    public MultiColorsPainterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFields(context);
    }

    public MultiColorsPainterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFields(context);
    }


    //endregion


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float lastX = event.getX();
        float lastY = event.getY();


        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                initPainterBox();
                path.moveTo(lastX,lastY);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(lastX,lastY);
                break;
            case MotionEvent.ACTION_UP:
                path.lineTo(lastX, lastY);
                path = null;
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
        drawBoxes(canvas);

    }

    private void drawBoxes(Canvas canvas) {

        for(PainterBox box:painterBoxes){
            canvas.drawPath(box.path,box.paint);
        }
    }

    public void clear(){
        painterBoxes.clear();
        invalidate();
    }

    public void undo(){
        int id;
        PainterBox box;
        if(!boxIds.isEmpty()){
            id = boxIds.get(boxIds.size() - 1);
            box = getPainterBoxByID(id);
            if(box != null){
                painterBoxes.remove(box);
                boxIds.remove(boxIds.size() - 1);
                invalidate();
            }
        }
    }

    private PainterBox getPainterBoxByID(int id) {
        PainterBox box = null;
        for(PainterBox painterBox: painterBoxes){
            if(painterBox.id == id){
                box = painterBox;
                break;
            }
        }
        return box;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private class PainterBox {
        private int id;
        private Paint paint;
        private Path path;

        PainterBox(Paint paint,Path path){
            id = UUID.randomUUID().hashCode();
            this.paint = paint;
            this.path = path;
        }
    }
}
