package com.example.mma;

public class ScheduleBlock {
    private int Image;
    private String Text;

    public ScheduleBlock(int image, String text){
        Image = image;
        Text = text;
    }

    public int getImage(){
        return Image;
    }
    public String getText(){
        return Text;
    }
}
