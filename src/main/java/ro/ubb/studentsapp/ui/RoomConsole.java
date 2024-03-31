package ro.ubb.studentsapp.ui;
import ro.ubb.studentsapp.domain.Room;
import ro.ubb.studentsapp.operations.RoomService;

import java.util.List;
import java.util.Scanner;

public class RoomConsole {

    private RoomService roomService;
    public RoomConsole(RoomService roomService) {
        this.roomService = roomService;
    }

    public void runConsole(){
        readRoom();
        displayRooms();
//        updateRoomId();
//        deleteRoom();
//        displayRooms();
        System.out.println();
        sortRooms();
        System.out.println();
        sortRoomsByBeds();
    }

    private void readRoom(){
        try {
            int  flag = 1;
            while (flag == 1) {
                Room room = new Room();
                Scanner scanner = new Scanner(System.in);
                System.out.println("Introduceti id-ul camerei: ");
                room.setId(scanner.nextInt());
                System.out.println("Introduceti numele camerei: ");
                room.setRoomName(scanner.next());
                System.out.println("Introduceti pretul pe noapte: ");
                room.setPricePerNight(scanner.nextFloat());
                System.out.println("Introduceti numarul de paturi - intre 1 si 5: ");
                room.setBedsNumber(scanner.nextInt());
                System.out.println("Introduceti tipul camerei :");
                room.setType(scanner.next());
                try {
                    roomService.addRoom(room);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Doriti sa mai cititi o camera? 1=Da 0=Nu");
                flag = scanner.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Ceva nu a functionat, incercati din nou!");
        }
    }

    private void updateRoomId(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti id-ul de modificat: ");
        int idToModify = scanner.nextInt();
        System.out.println("Introduceti noul id: ");
        int newId = scanner.nextInt();
        roomService.updateRoom(idToModify, newId);
        List<Room> allRooms = roomService.getAllRooms();
        System.out.println(allRooms);
    }

    private void deleteRoom(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti id-ul camerei de sters: ");
        int idToDelete = scanner.nextInt();
        roomService.deleteRoom(idToDelete);
        List<Room> allRooms = roomService.getAllRooms();
        System.out.println(allRooms);
    }

    private void displayRooms(){
        if(roomService.getAllRooms().isEmpty()){
            System.out.println("Nu exista camere momentan.");
        }
        for (Room room : roomService.getAllRooms()) {
            System.out.println(room);
        }
    }

    private void sortRooms(){
        System.out.println("Camerele ordonate crescator in functie de media pret per noapte / numar de paturi:");
        List<Room> allRooms = roomService.getAllRooms();
        roomService.sortByPrice(allRooms);
        System.out.println(allRooms);
    }

    private List<Room> sortRoomsByBeds(){
        System.out.println("Media numărului de paturi din camerele de un anumit tip:");
        List<Room> allRooms = roomService.getAllRooms();
        roomService.sortByBedsNumber(allRooms);
        return allRooms;
    }
}
