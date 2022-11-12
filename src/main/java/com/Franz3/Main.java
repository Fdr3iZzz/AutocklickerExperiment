package com.Franz3;


import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main implements NativeKeyListener, NativeMouseListener {

    static int startX = 936;
    static int startY = 936;
    static int fightX = 936 ;
    static int fightY = 1030;
    static int anywhereX = 936;
    static int anywhereY = 936;

    static int generalY = 1021;
    static int oneY = 715;
    static int twoY = 829;
    static int threeY = 939;
    static int fourY = 1051;
    static int fiveY = 1163;
    static String path = System.getProperty("user.home") + "\\afkAutomation";


    public static void main(String[] args) throws InterruptedException, AWTException, IOException {
        try {
            GlobalScreen.registerNativeHook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(new Main());
        GlobalScreen.addNativeMouseListener(new Main());
        //check for folder and create if needed
        File file = new File(path);
        file.mkdirs();
        //set up robot
        Robot robot = new Robot();
        robot.setAutoDelay(10);
        //set up screenCapture
        Rectangle rec = new Rectangle(1037,751,550,1); //1037,751,550,1
        BufferedImage image = robot.createScreenCapture(rec);
        System.out.println("Starting...");
        Thread.sleep(2 * 1000);

        //click starten
        //click kämpfen
        //ulten
        //click irgendwo

        //ulten
        //screenshot
        //compare color -> maybe click

        //click start
        robot.mouseMove(startX, startY);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(2000);
        //click kämpfen
        robot.mouseMove(fightX, fightY);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        //screen
        ImageIO.write(image, "jpg", new File(path + "\\screen.jpg"));
        //ult

        //screen

        //click anywhere
        robot.mouseMove(anywhereX, anywhereY);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);



    }
    public boolean hotkey = false;
    public boolean hotkey2 = false;
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        //----------------quitShortcut---------------------------
        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
            hotkey = true;
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_ALT && hotkey) {
            hotkey2 = true;
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_Q && hotkey && hotkey2) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Exiting program");
            hotkey = false;
            hotkey2 = false;
        }
        //-------------------------------------------------------
    }
    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_ALT || e.getKeyCode() == NativeKeyEvent.VC_CONTROL || e.getKeyCode() == NativeKeyEvent.VC_Q){
            hotkey = false;
            hotkey2 = false;
        }
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent e) {
        if (e.getButton() == NativeMouseEvent.BUTTON1){
            System.out.println("X: " + e.getY() + " Y: " + e.getX());
        }
    }
}