package com.example.mma;

///CLASS REPRESENTS THE HOURLY BLOCK IN THE DAYS SCHEDULE (MR)
public class ScheduleBlock {

    //VARIABLES INSIDE BLOCK (MR)
    private int Image;
    private String Text;

    //CONSTRUCTOR FOR BLOCK (MR)
    public ScheduleBlock(int image, String text){
        Image = image;
        Text = text;
    }

    //GETTERS (MR)
    public int getImage(){
        return Image;
    }
    public String getText(){
        return Text;
    }
}
