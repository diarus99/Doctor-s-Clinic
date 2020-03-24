package Domain;

import java.util.Objects;

public class Room {
    private String name;
    private int numberOfMinutes; //number of minutes used to consult until this moment in this room

    public Room(String name, int numberOfMinutes) {
        this.name = name;
        this.numberOfMinutes = numberOfMinutes;
    }

    public Room() {
        this.name="";
        this.numberOfMinutes=0;
    }
////getter and setter:

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfMinutes() {
        return numberOfMinutes;
    }

    public void setNumberOfMinutes(int numberOfMinutes) {
        this.numberOfMinutes = numberOfMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return numberOfMinutes == room.numberOfMinutes &&
                Objects.equals(name, room.name);
    }

}
