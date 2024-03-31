// CRUD Camere: id cameră, denumire, preț pe noapte, număr paturi, tip.
// ID-ul trebuie să fie unic, denumirea nenulă, prețul pe noapte float, numărul paturilor pozitiv între 1 și 5, iar tipul unul dintre Regular, Business, Executive, VIP.

package ro.ubb.studentsapp.domain;

public class Room {

    int id;
    String roomName;
    float pricePerNight;
    int bedsNumber;
    String type;

    public Room(){

    }

    public Room(int id, String roomName, float pricePerNight, int bedsNumber, String type){
        this.id = id;
        this.roomName = roomName;
        this.pricePerNight = pricePerNight;
        this.bedsNumber = bedsNumber;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
            this.roomName = roomName;
    }

    public float getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(float pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getBedsNumber() {
        return bedsNumber;
    }

    public void setBedsNumber(int bedsNumber) {
            this.bedsNumber = bedsNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
            this.type = type;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", bedsNumber=" + bedsNumber +
                ", type='" + type + '\'' +
                '}';
    }
}
