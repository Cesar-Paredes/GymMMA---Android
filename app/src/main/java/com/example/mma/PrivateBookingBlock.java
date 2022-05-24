package com.example.mma;

public class PrivateBookingBlock {
    //VARIABLES INSIDE BLOCK (MR)
    private int Image;
    private String Text;

    //CONSTRUCTOR FOR BLOCK (MR)
    public PrivateBookingBlock(int image, String text){
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
