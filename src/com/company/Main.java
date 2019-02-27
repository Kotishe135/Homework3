package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

class BgPanel extends JPanel{
    public void paintComponent(Graphics g){
        Image im = null;
        try {
            im = ImageIO.read(new File("cam.png"));
        } catch (IOException e) {}
        g.drawImage(im, 0, 0, null);
    }
}

public class Main {

    public static void createWindow(){
        Camera cam = new Camera();

        JFrame camera = new JFrame("Фотоаппарат");
        camera.setContentPane(new BgPanel());
        camera.setLayout(null);
        camera.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        camera.setUndecorated(true);
        camera.setBounds((int)((Toolkit.getDefaultToolkit().getScreenSize().width-832)/2), (int)((Toolkit.getDefaultToolkit().getScreenSize().height-551)/2), 832, 551);


        JButton rewind = new JButton("Перемотать");
        rewind.setBounds(75, 75, 130, 20);
        rewind.setMargin(new Insets(0, 0, 0, 0));

        JButton photo = new JButton("Фото");
        photo.setBounds(camera.getWidth()-140, 75, 40, 20);
        photo.setMargin(new Insets(0, 0, 0, 0));

        JButton reset = new JButton("Сменить пленку");
        reset.setBounds(75, 195, 130, 20);
        reset.setMargin(new Insets(0, 0, 0, 0));

        JLabel currentPhoto = new JLabel();
        currentPhoto.setBounds(275, 250, 300, 200);

        photo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cam.makeAPhoto();
                if (cam.getPhoto()!=null) {
                    Image scaled = cam.getPhoto().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(scaled);
                    currentPhoto.setIcon(imageIcon);
                    camera.update(camera.getGraphics());
                }
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cam.resetSlick()) {
                    currentPhoto.setIcon(null);
                }
            }
        });

        rewind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cam.rewind()){
                    currentPhoto.setIcon(null);
                }
            }
        });

        JButton close = new JButton("Закрыть");
        close.setBounds(camera.getWidth()-75, 0, 75, 20);
        close.setMargin(new Insets(0,0,0,0));
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                camera.dispose();
            }
        });

        camera.getContentPane().add(rewind);
        camera.getContentPane().add(photo);
        camera.getContentPane().add(reset);
        camera.getContentPane().add(currentPhoto);
        camera.getContentPane().add(close);
        camera.setVisible(true);
    }

    public static void main(String[] args) {
        Camera cam = new Camera();
        createWindow();
    }
}
