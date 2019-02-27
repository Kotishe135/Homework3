package com.company;

import javax.swing.*;
import java.awt.Image;

public class Slick {
    private int volume = 10;
    private int amountPhoto = 0;
    private int numberOfPhoto = 0;
    private Image[] photoSlick = new Image[volume];

    public boolean canReset(){
        if (amountPhoto < volume){
            JOptionPane.showMessageDialog(null, "Ресурс плёнки использован не полностью", "Невозможно сменить плёнку", 2);
            return false;
        }
        return true;
    }

    public boolean rewind(){
        if (canRewind()) {
            numberOfPhoto++;
            return true;
        }else {
            return false;
        }
    }

    public boolean canRewind(){
        if (photoSlick[numberOfPhoto] == null) {
            JOptionPane.showMessageDialog(null, "Фото не сделано", "Невозможно перемотать пленку", 0);
            return false;
        }
        if (amountPhoto >= volume){
            JOptionPane.showMessageDialog(null, "Плёнка заполнена", "Невозможно перемотать пленку", 0);
            return false;
        }
        return true;
    }

    public void addPhoto(Image photo){
        photoSlick[numberOfPhoto] = photo;
        amountPhoto++;
    }

    public Image getPhoto(int i){
        return photoSlick[i];
    }

    public Image getCurrentPhoto(){
        return photoSlick[numberOfPhoto];
    }

    public boolean canAddPhoto(){
        if (amountPhoto >= volume){
            JOptionPane.showMessageDialog(null, "Замените плёнку", "Невозможно сделать фото", 0);
            return false;
        }
        if (photoSlick[numberOfPhoto] != null){
            JOptionPane.showMessageDialog(null, "Перемотайте плёнку", "Невозможно сделать фото", 0);
            return false;
        }
        return true;
    }

}
