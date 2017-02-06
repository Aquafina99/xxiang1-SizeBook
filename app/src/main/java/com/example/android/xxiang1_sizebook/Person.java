package com.example.android.xxiang1_sizebook;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by ceciliaxiang on 2017-01-31.
 */

public class Person implements Serializable {
    private String name;
    private Calendar date;
    private String neck;
    private String bust;
    private String chest;
    private String waist;
    private String hip;
    private String inseam;
    private String comment;

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getNeck() {
        return neck;
    }

    public void setNeck(String neck) {
        this.neck = oneDecimal(neck);
    }

    public String getBust() {
        return bust;
    }

    public void setBust(String bust) {
        this.bust = oneDecimal(bust);
    }

    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = oneDecimal(chest);
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = oneDecimal(waist);
    }

    public String getHip() {
        return hip;
    }

    public void setHip(String hip) {
        this.hip = oneDecimal(hip);
    }

    public String getInseam() {
        return inseam;
    }

    public void setInseam(String inseam) {
        this.inseam = oneDecimal(inseam);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    //taken from http://stackoverflow.com/questions/10959424/show-only-two-digit-after-decimal
    //by: dumazy, bluish
    //modified to 1 decimal

    public String oneDecimal(String s){
        if (s.isEmpty()){
            return s;
        }
        double num = Double.parseDouble(s);
        String ss = String.format("%.1f", num);
        return ss;
    }


    @Override
    public String toString(){
        return this.name;
    }
}
