package ro.ubb.studentsapp;

import ro.ubb.studentsapp.operations.RoomService;
import ro.ubb.studentsapp.ui.RoomConsole;

public class Main {

    public static void main(String[] args){
        RoomService roomService = new RoomService();
        RoomConsole roomConsole = new RoomConsole(roomService);

        roomConsole.runConsole();
    }

}
