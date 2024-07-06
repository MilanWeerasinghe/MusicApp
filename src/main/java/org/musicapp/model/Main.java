package org.musicapp.model;

import org.musicapp.controller.SongController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SongController songController = new SongController();
        songController.manageSongs();



    }
}