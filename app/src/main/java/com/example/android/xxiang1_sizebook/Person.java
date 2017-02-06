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

    /**
     * Instantiates a new Person.
     */
    public Person() {

    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * Gets neck.
     *
     * @return the neck
     */
    public String getNeck() {
        return neck;
    }

    /**
     * Sets neck.
     *
     * @param neck the neck
     */
    public void setNeck(String neck) {
        this.neck = oneDecimal(neck);
    }

    /**
     * Gets bust.
     *
     * @return the bust
     */
    public String getBust() {
        return bust;
    }

    /**
     * Sets bust.
     *
     * @param bust the bust
     */
    public void setBust(String bust) {
        this.bust = oneDecimal(bust);
    }

    /**
     * Gets chest.
     *
     * @return the chest
     */
    public String getChest() {
        return chest;
    }

    /**
     * Sets chest.
     *
     * @param chest the chest
     */
    public void setChest(String chest) {
        this.chest = oneDecimal(chest);
    }

    /**
     * Gets waist.
     *
     * @return the waist
     */
    public String getWaist() {
        return waist;
    }

    /**
     * Sets waist.
     *
     * @param waist the waist
     */
    public void setWaist(String waist) {
        this.waist = oneDecimal(waist);
    }

    /**
     * Gets hip.
     *
     * @return the hip
     */
    public String getHip() {
        return hip;
    }

    /**
     * Sets hip.
     *
     * @param hip the hip
     */
    public void setHip(String hip) {
        this.hip = oneDecimal(hip);
    }

    /**
     * Gets inseam.
     *
     * @return the inseam
     */
    public String getInseam() {
        return inseam;
    }

    /**
     * Sets inseam.
     *
     * @param inseam the inseam
     */
    public void setInseam(String inseam) {
        this.inseam = oneDecimal(inseam);
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }



    //taken from http://stackoverflow.com/questions/10959424/show-only-two-digit-after-decimal
    //by: dumazy, bluish
    //modified to 1 decimal

    /**
     * One decimal string.
     *
     * @param s the s
     * @return the string
     */
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
