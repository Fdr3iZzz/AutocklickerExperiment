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

    static int generalY = 1024;
    static int oneX = 752;
    static int twoX = 863;
    static int threeX = 974;
    static int fourX = 1085;
    static int fiveX = 1196;
    static String pixelColor = "fff27f34";    //fff27f34    f27f34
    static String path = System.getProperty("user.home") + "\\afkAutomation";
    static Robot robot;
    static String getPixelHex;
    static String pixelColorWin = "ff070302";
    static String pixelColorLoose = "ff040308";

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

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
        //Robot robot = new Robot();
        robot.setAutoDelay(10);
        //set up screenCapture
        Rectangle rec = new Rectangle(738,1024,458,1);
        System.out.println("Starting...");
        System.out.println("Press STRG + ALT + Q to quit");
        Thread.sleep(2 * 1000);

        //click start
        //click fight
        //ult
        //click somewhere

        //ult
        //screenshot
        //compare color -> maybe click

        //click start
        robot.mouseMove(startX, startY);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(2000);
        //click fight
        robot.mouseMove(fightX, fightY);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        //screenshot
        BufferedImage image = robot.createScreenCapture(rec);
        ImageIO.write(image, "jpg", new File(path + "\\screen.jpg"));
            while (true) {
                getPixelHex = Integer.toHexString(robot.getPixelColor(twoX, generalY).getRGB());
                //compareColor
                if (getPixelHex.equals(pixelColor)) {
                    //ult if correct
                    robot.mouseMove(twoX, generalY);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                } else if (getPixelHex.equals(pixelColorWin) || getPixelHex.equals(pixelColorLoose)) {
                    System.out.println("Win or Loose");
                    break;
                }
                getPixelHex = Integer.toHexString(robot.getPixelColor(oneX, generalY).getRGB());
                if (getPixelHex.equals(pixelColor)) {
                    robot.mouseMove(oneX, generalY);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                }
                getPixelHex = Integer.toHexString(robot.getPixelColor(threeX, generalY).getRGB());
                if (getPixelHex.equals(pixelColor)) {
                    robot.mouseMove(threeX, generalY);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                }
                getPixelHex = Integer.toHexString(robot.getPixelColor(fourX, generalY).getRGB());
                if (getPixelHex.equals(pixelColor)) {
                    robot.mouseMove(fourX, generalY);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                }
                getPixelHex = Integer.toHexString(robot.getPixelColor(fiveX, generalY).getRGB());
                if (getPixelHex.equals(pixelColor)) {
                    robot.mouseMove(fiveX, generalY);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                }
            }

        //ult
        //check win or loose


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
            System.out.println("X: " + e.getX() + " Y: " + e.getY());
        }
    }
}