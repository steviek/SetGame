package com.sixbynine.set.object;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.sixbynine.set.R;

/**
 * Created by steviekideckel on 10/25/14.
 */
public class Card implements Parcelable{

    private Count mCount;
    private Color mColor;
    private Shading mShading;
    private Shape mShape;

    public static final int SMALL = 0;
    public static final int MEDIUM = 1;
    public static final int LARGE = 2;

    private int mBackgroundColor;

    public enum Count {
        ONE(0,1),
        TWO(1,2),
        THREE(2,3);
        int key;
        int value;

        Count(int key, int value){
            this.key = key;
            this.value = value;
        }

        public static Count fromKey(int key){
            for(Count c : values()){
                if(c.key == key){
                    return c;
                }
            }
            return null;
        }
    }

    public enum Color{
        RED(0,R.color.red),
        BLUE(1,R.color.purple),
        GREEN(2,R.color.green);
        int color;
        int key;

        Color(int key, int color){
            this.key = key;
            this.color = color;
        }

        public static Color fromKey(int key){
            for(Color c : values()){
                if(c.key == key){
                    return c;
                }
            }
            return null;
        }
    }

    public enum Shading{
        SOLID(0),
        STRIPED(1),
        OPEN(2);
        int key;

        Shading(int key){
            this.key = key;
        }

        public static Shading fromKey(int key){
            for(Shading c : values()){
                if(c.key == key){
                    return c;
                }
            }
            return null;
        }

    }

    public enum Shape{
        OVAL(0),
        DIAMOND(1),
        SQUIGGLE(2);
        int key;

        Shape(int key){
            this.key = key;
        }

        public static Shape fromKey(int key){
            for(Shape c : values()){
                if(c.key == key){
                    return c;
                }
            }
            return null;
        }
    }

    public void drawCard(Resources resources, Canvas canvas, int size){
        if(size < 0 || size > 3){
            throw new IllegalArgumentException("size must be one of SMALL, MEDIUM, LARGE");
        }
        switch(size){
            case SMALL:
            case LARGE:
            case MEDIUM:
            default:
                drawCard(resources, canvas, 0, 0, resources.getDimensionPixelOffset(R.dimen.card_medium_width), resources.getDimensionPixelOffset(R.dimen.card_medium_height));
        }

    }

    public Count getCount() {
        return mCount;
    }

    public void setCount(Count count) {
        this.mCount = mCount;
    }

    public Color getColor() {
        return mColor;
    }

    public void setColor(Color color) {
        this.mColor = mColor;
    }

    public Shading getShading() {
        return mShading;
    }

    public void setShading(Shading shading) {
        this.mShading = mShading;
    }

    public Shape getShape() {
        return mShape;
    }

    public void setShape(Shape shape) {
        this.mShape = mShape;
    }

    public String toString(){
        return mCount.name() + " " + mColor.name() + " " + mShading.name() + " " + mShape.name();
    }

    /**
     * draws the card on the canvas
     * @param resources used to access the color and other resources
     * @param canvas the canvas to draw on
     * @param x the top-left x-coordinate of the card
     * @param y the top-left y-coordinate of the card
     * @param width the width of the card
     * @param height the height of the card
     */
    public void drawCard(Resources resources, Canvas canvas, int x, int y, int width, int height){
        mBackgroundColor = resources.getColor(R.color.card_background);
        drawBackground(resources, canvas, x, y, width, height);

        int shapeWidth = (width * 100) / 165;
        int shapeHeght = (height * 60) / 263;

        switch(mCount){
            case TWO:
                drawShape(resources, canvas, x + width/2, y + (height * 37) / 100, shapeWidth, shapeHeght);
                drawShape(resources, canvas, x + width/2, y + (height * 63) / 100, shapeWidth, shapeHeght);
                break;
            case THREE:
                drawShape(resources, canvas, x + width/2, y + (height * 23) / 100, shapeWidth, shapeHeght);
                drawShape(resources, canvas, x + width/2, y + (height * 77) / 100, shapeWidth, shapeHeght);
            case ONE:
                drawShape(resources, canvas, x + width/2, y + height/2, shapeWidth, shapeHeght);
                break;
        }
    }

    /**
     * draws the shape at the given location
     * @param resources used to access the color and other resources
     * @param canvas the canvas to draw on
     * @param x the centre x-coordinate of the shape
     * @param y the centre y-coordinate of the shape
     * @param width the width of the shape
     * @param height the height of the shape
     */
    private void drawShape(Resources resources, Canvas canvas, int x, int y, int width, int height){
        int drawableId = 0;
        int color = resources.getColor(mColor.color);

        int left = x - width/2;
        int top = y - height/2;
        int right = x + width/2;
        int bottom = y + height/2;
        RectF rect = new RectF(left, top, right, bottom);

        switch(mShape){
            case OVAL:
                switch(mShading){
                    case SOLID:
                        drawableId = R.drawable.oval_solid;
                        break;
                    case OPEN:
                        drawableId = R.drawable.oval_open;
                        break;
                }
                break;
        }

        Drawable drawable = resources.getDrawable(drawableId);
        drawable.setColorFilter(new
                PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY));

        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        // Scale it to 50 x 50
        BitmapDrawable d = new BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, width, height, true));

        canvas.drawBitmap(d.getBitmap(), left, top, null);

    }

    /**
     * draw the background of the card
     * @param resources used to access the color and other resources
     * @param canvas the canvas to draw on
     * @param x the top-left x-coordinate of the card
     * @param y the top-left y-coordinate of the card
     * @param width the width of the card
     * @param height the height of the card
     */
    private void drawBackground(Resources resources, Canvas canvas, int x, int y, int width, int height){
        Paint paint = new Paint();
        paint.setColor(mBackgroundColor);
        canvas.drawRect(x,y,x+width,y+height,paint);
    }


    //TODO do other stuff

    public static boolean formsSet(Card one, Card two, Card three){
        return false; //TODO make this work
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mCount.key);
        dest.writeInt(mColor.key);
        dest.writeInt(mShading.key);
        dest.writeInt(mShape.key);
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel source) {
            Card card = new Card();
            card.mCount = Count.fromKey(source.readInt());
            card.mColor = Color.fromKey(source.readInt());
            card.mShading = Shading.fromKey(source.readInt());
            card.mShape = Shape.fromKey(source.readInt());
            return card;
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
}
