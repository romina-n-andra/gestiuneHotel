// CREATE, READ, UPDATE, DELETE;

package ro.ubb.studentsapp.operations;
import ro.ubb.studentsapp.domain.Room;

import java.util.*;

import static java.util.Collections.sort;

public class RoomService {
    private List<Room> roomsList;

    public RoomService() {
        this.roomsList = new ArrayList<>();
    }

    public List<Room> getAllRooms() {
        return roomsList;
    }

    public void addRoom(Room room) throws Exception {
        for (Room r : roomsList) {
            if (r.getId() == room.getId()) {
                throw new Exception("Nu ati respectat regulile de introducere! Incercati din nou.");
            }
        }
        if (room.getRoomName() == null) {
            throw new Exception("Nu ati respectat regulile de introducere! Incercati din nou.");
        }
        if (room.getBedsNumber() < 0 || room.getBedsNumber() > 5) {
            throw new Exception("Nu ati respectat regulile de introducere! Incercati din nou.");
        }
        if (!room.getType().equals("Regular") &&
                !room.getType().equals("Business") &&
                !room.getType().equals("Executive") &&
                !room.getType().equals("VIP")) {
            throw new Exception("Nu ati respectat regulile de introducere! Incercati din nou.");
        }
        roomsList.add(room);
    }


    public void updateRoom(int idUpdate, int newId) {
        for (Room r : roomsList) {
            if (r.getId() == idUpdate) {
                r.setId(newId);
            }
        }
    }

    public void deleteRoom(int idToDelete) {
        for (int i = 0; i < roomsList.size(); i++) {
            if (roomsList.get(i).getId() == idToDelete) {
                roomsList.remove(roomsList.get(i));
            }
        }
    }

    private Room findbyId(int id) {
        for (Room room : roomsList) {
            if (room.getId() == id) {
                return room;
            }
        }
        return null;
    }


    public void save(Room room) {
        if (findbyId(room.getId()) != null) {
            throw new RuntimeException("Duplicate id!");
        }
        roomsList.add(room);
    }

    public void sortByPrice(List<Room> roomsList) {

        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
                public int compare(Room room1, Room room2) {
                    double avgPricePerBed1 = room1.getPricePerNight() / room1.getBedsNumber();
                    double avgPricePerBed2 = room2.getPricePerNight() / room2.getBedsNumber();
                    return Double.compare(avgPricePerBed1, avgPricePerBed2);
                }
            };
            Collections.sort(roomsList, comparator);
        }

//        for (Room room : roomsList) {
//            double media = room.getPricePerNight() / room.getBedsNumber();

    public void sortByBedsNumber(List<Room> roomsList) {
        Map<String, Double> roomTypesAndBeds = new HashMap<>();
        roomTypesAndBeds.put("Regular", 0.0);
        roomTypesAndBeds.put("Business", 0.0);
        roomTypesAndBeds.put("Executive", 0.0);
        roomTypesAndBeds.put("VIP", 0.0);

        for (Room room : roomsList) {
            String roomType = room.getType();
            double currentBeds = roomTypesAndBeds.getOrDefault(roomType, 0.0);
            roomTypesAndBeds.put(roomType, currentBeds + room.getBedsNumber());
        }

        Map<String, Integer> roomTypesCount = new HashMap<>();
        for (Room room : roomsList) {
            String roomType = room.getType();
            roomTypesCount.put(roomType, roomTypesCount.getOrDefault(roomType, 0) + 1);
        }

        Set<String> keys = roomTypesAndBeds.keySet();
        for (String roomType : keys) {
            double totalBeds = roomTypesAndBeds.get(roomType);
            int totalRooms = roomTypesCount.getOrDefault(roomType, 0);
            double avgBeds = totalBeds / totalRooms;
            roomTypesAndBeds.put(roomType, avgBeds);
        }

        for (String roomType : keys) {
            System.out.println("Media de paturi pentru camera de tipul " + roomType + ": " + roomTypesAndBeds.get(roomType));
        }
    }
}
