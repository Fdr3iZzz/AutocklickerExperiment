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
import java.util.Scanner;

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
    static boolean [] selectedUlting = new boolean[5];
    int winsGained;
    int loosesGained;
    public static String ultsSelected = "";


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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Starting...");
        System.out.println("Press STRG + ALT + Q to quit");
        //get user input
        while (true) {
                System.out.println("Which heroes should ult?");
                System.out.println("1, 2, 3, 4, 5. exit and start with 6. reset with 7");
                if (!ultsSelected.equals("")){
                    System.out.println("Currently selected: " + ultsSelected);
                }
                //take input
                int Input = scanner.nextInt();
                //repeat if wrong
                if (Input > 7 || Input < 1) {
                    System.out.println("wrong Input");
                    continue;
                }
                if (Input == 1) {
                    selectedUlting [0] = true;
                    ultsSelected = ultsSelected + "1, ";
                }
                if (Input == 2) {
                    selectedUlting [1] = true;
                    ultsSelected = ultsSelected + "2, ";
                }
                if (Input == 3) {
                    selectedUlting [2] = true;
                    ultsSelected = ultsSelected + "3, ";
                }
                if (Input == 4) {
                    selectedUlting [3] = true;
                    ultsSelected = ultsSelected + "4, ";
                }
                if (Input == 5) {
                    selectedUlting [4] = true;
                    ultsSelected = ultsSelected + "5, ";
                }
                // reset
                if (Input == 7){
                    selectedUlting [0] = false;
                    selectedUlting [1] = false;
                    selectedUlting [2] = false;
                    selectedUlting [3] = false;
                    selectedUlting [4] = false;
                    ultsSelected = "";
                    System.out.println("Inputs reseted");
                }
                //exit on 6
                if (Input == 6) {

                    System.out.println("Ulting slots selected: " + ultsSelected);
                    break;
                }
            }
                Thread.sleep(2 * 1000);
        /*
        Make user Input System better
        autorestart:
        detect startButton
        maybe detect bossfight
        detect fightButton

         */

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
            while (true) {
                //compareColor and ult if correct and selected
                getPixelHex = Integer.toHexString(robot.getPixelColor(oneX, generalY).getRGB());
                if (getPixelHex.equals(pixelColor) && selectedUlting[0]) {
                    robot.mouseMove(oneX, generalY);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                }
                getPixelHex = Integer.toHexString(robot.getPixelColor(twoX, generalY).getRGB());
                if (getPixelHex.equals(pixelColor) && selectedUlting[1]) {
                    robot.mouseMove(twoX, generalY);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                }
                getPixelHex = Integer.toHexString(robot.getPixelColor(threeX, generalY).getRGB());
                if (getPixelHex.equals(pixelColor) && selectedUlting[2]) {
                    robot.mouseMove(threeX, generalY);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                }
                getPixelHex = Integer.toHexString(robot.getPixelColor(fourX, generalY).getRGB());
                if (getPixelHex.equals(pixelColor) && selectedUlting[3]) {
                    robot.mouseMove(fourX, generalY);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                }
                getPixelHex = Integer.toHexString(robot.getPixelColor(fiveX, generalY).getRGB());
                if (getPixelHex.equals(pixelColor) && selectedUlting[4]) {
                    robot.mouseMove(fiveX, generalY);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                }
                //detect win or loose
                if (getPixelHex.equals(pixelColorWin) || getPixelHex.equals(pixelColorLoose)) {
                    System.out.println("Win or Loose");
                    break;
                }
            }

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
                System.out.println("Exiting program");
                GlobalScreen.unregisterNativeHook();
                hotkey = false;
                hotkey2 = false;
                System.exit(0);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
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