package com.example.user.drawingapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.user.drawingapp.R;

/**
 * Created by User on 10/03/2016.
 */
public class SmileView extends View {

    //region Constants
    private static final float MAX_SCALE_VALUE = 2.0f;

    private static final float MIN_SCALE_VALUE = 0.5f;

    private static final int GROW_CODE = 101;

    private static final int LITTLE_CODE = 102;

    private static final float MOVE_OFFSET = 10;

    private static final int MOVE_DOWN_CODE = 301;

    private static final int MOVE_UP_CODE = 302;

    private static final float OFFSET = 0.1f;

    //endregion
    //region Instance Variables
    private Paint paint;

    private Drawable shape;

    private Path path;

    private float angle = 0;

    private float scaleOffset = MIN_SCALE_VALUE;

    private int currentCode = GROW_CODE;

    private float posY;

    private int currentMoveCode = MOVE_DOWN_CODE;
    //endregion
    public SmileView(Context context) {
        super(context);
        posY = MOVE_OFFSET;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        shape = context.getResources().getDrawable(R.drawable.background);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        shape.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        shape.draw(canvas);
        drawSmile(canvas);
        setScale();
        setAngle();
        setPosY();


    }

    private void setPosY() {
        if(posY <= -120){
            currentMoveCode = MOVE_DOWN_CODE;
        }
        else if(posY >= 300){
            currentMoveCode = MOVE_UP_CODE;
        }

        if (currentMoveCode == MOVE_DOWN_CODE){
            posY += MOVE_OFFSET;
        }
        else if(currentMoveCode == MOVE_UP_CODE){
            posY -= MOVE_OFFSET;
        }



    }

    private void setAngle() {
        angle += 30;
    }

    private void setScale() {
        if(scaleOffset >= MAX_SCALE_VALUE)currentCode = LITTLE_CODE;
        if(scaleOffset <= MIN_SCALE_VALUE)currentCode = GROW_CODE;
        if(currentCode == GROW_CODE){
            scaleOffset += OFFSET;
        }
        else {
            scaleOffset -= OFFSET;
        }
    }

    private void drawSmile(Canvas canvas) {

        final int density = (int) getContext().getResources().getDisplayMetrics().density;

        //drawBackground(canvas);
        //drawGradientBackground(canvas);
        drawSmileHead(canvas, density);
        drawSmileEyes(canvas, density);
        drawSmileNose(canvas, density);
        drawSmileMouth(canvas, density);


    }

    private void drawSmileMouth(Canvas canvas, int density) {

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);

        path.moveTo(150 * density, 210 * density);
        path.quadTo(180 * density, 260 * density, 210 * density, 210 * density);


        canvas.save();
        canvas.translate(0 * density, posY * density);
        canvas.rotate(angle, 180 * density, 200 * density);
        canvas.scale(scaleOffset, scaleOffset,180 * density,200 * density);

        canvas.drawPath(path, paint);
        canvas.restore();

        paint.reset();
        path.reset();
    }

    private void drawSmileNose(Canvas canvas, int density) {
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(20);

        path.moveTo(180 * density, 160 * density);
        path.lineTo(180 * density, 210 * density);

        path.addCircle(187 * density, 205 * density, 5 * density, Path.Direction.CW);
        path.addCircle(173 * density, 205 * density, 5 * density, Path.Direction.CW);


        canvas.save();
        canvas.translate(0 * density, posY * density);
        canvas.rotate(angle, 180 * density, 200 * density);
        canvas.scale(scaleOffset, scaleOffset, 180 * density,200 * density);

        canvas.drawPath(path, paint);
        canvas.restore();

        paint.reset();
        path.reset();
    }

    private void drawSmileEyes(Canvas canvas, int density) {
        paint.setARGB(200, 0, 255, 255);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        canvas.save();
        canvas.translate(0 * density, posY * density);
        canvas.rotate(angle, 180 * density, 200 * density);
        canvas.scale(scaleOffset, scaleOffset, 180 * density, 200 * density);

        canvas.drawCircle(150 * density, 170 * density, 20, paint);
        canvas.restore();

        paint.reset();
        paint.setARGB(200, 0, 255, 255);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        canvas.save();
        canvas.translate(0 * density, posY * density);
        canvas.rotate(angle, 180 * density, 200 * density);
        canvas.scale(scaleOffset, scaleOffset,180 * density, 200 * density);
        canvas.drawCircle(210 * density, 170 * density, 20, paint);
        canvas.restore();
        paint.reset();
    }

    private void drawSmileHead(Canvas canvas, int density) {
        paint.setARGB(200, 255, 255, 0);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        canvas.save();
        canvas.translate(0 * density, posY * density);
        canvas.rotate(angle, 180 * density, 200 * density);
        canvas.scale(scaleOffset,scaleOffset,180 * density, 200 * density);
        canvas.drawCircle(180 * density, 200 * density, 200, paint);
        canvas.restore();

        paint.reset();
    }


    private void drawGradientBackground(Canvas canvas){
        paint.setShader(new LinearGradient(0, 0, 0, getHeight(), Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));
        canvas.drawPaint(paint);
        paint.reset();
    }
}
