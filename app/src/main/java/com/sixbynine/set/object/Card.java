package com.sixbynine.set.object;

/**
 * Created by steviekideckel on 10/25/14.
 */
public class Card {

    private ItemNumber mItemNumber;

    public enum ItemNumber {
        ONE(1),
        TWO(2),
        THREE(3);
        int value;

        ItemNumber(int value){
            this.value = value;
        }
    }


    //TODO do other stuff

    public static boolean formsSet(Card one, Card two, Card three){
        return false; //TODO make this work
    }



}
