package com.sixbynine.set.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by steviekideckel on 11/2/14.
 */
public class Deck extends ArrayList<Card> implements Parcelable{

    public Deck(){
        super();
    }

    public Deck(int size){
        super(size);
    }

    public static Deck newDeck(){
        Deck deck = new Deck();
        for(int i = 0; i < 81; i ++){
            Card.Color color = Card.Color.fromKey(i/27);
            Card.Count count = Card.Count.fromKey((i/9)%3);
            Card.Shading shading = Card.Shading.fromKey((i/3)%3);
            Card.Shape shape = Card.Shape.fromKey(i%3);
            Card card = new Card();
            card.setColor(color);
            card.setCount(count);
            card.setShading(shading);
            card.setShape(shape);
            deck.add(card);
        }
        return deck;
    }

    public void shuffle(){
        Collections.shuffle(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        int size = size();
        dest.writeInt(size);
        for(int i = 0; i < size; i ++){
            dest.writeParcelable(get(i), flags);
        }
    }

    public static final Creator<Deck> CREATOR = new Creator<Deck>() {
        @Override
        public Deck createFromParcel(Parcel source) {
            int size = source.readInt();
            Deck deck = new Deck(size);

            for(int i = 0; i < size; i ++){
                deck.add((Card) source.readParcelable(Card.class.getClassLoader()));
            }
            return deck;
        }

        @Override
        public Deck[] newArray(int size) {
            return new Deck[size];
        }
    };
}
