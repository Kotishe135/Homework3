package com.company;

import javax.swing.*;
import java.awt.*;

public class Camera {
    private SlickBox slickBox = new SlickBox();
    private Slick slick = null;

    public boolean resetSlick(){
        if (slick == null){
            slick = new Slick();
            return true;
        }else {
            if (slick.canReset()) {
                slickBox.addSlick(slick);
                slick = new Slick();
                return true;
            }else{
                return false;
            }
        }
    }

    public boolean rewind(){
        if (slick!=null){
            return slick.rewind();
        }else{
            JOptionPane.showMessageDialog(null, "Плёнка не может быть перемотана, потому что она не существует в фотоаппарате");
            return false;
        }
    }

    public boolean canMakeAPhoto(){
        if (slick == null){
            JOptionPane.showMessageDialog(null, "В фотоаппарате нет плёнки", "Невозможно сделать фото", 0);
        }
        return slick != null && slick.canAddPhoto();
    }

    public void makeAPhoto(){
        if (canMakeAPhoto()) {
            try {
                Image photo = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                slick.addPhoto(photo);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }

    public Image getPhoto(){
        return slick.getCurrentPhoto();
    }

}
