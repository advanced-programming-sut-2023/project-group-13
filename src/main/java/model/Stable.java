package model;

public class Stable {
    private final int capacity = 4 ;
    private int CurrentNumberOfHorses;

    public Stable() {
        CurrentNumberOfHorses = 4;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentNumberOfHorses() {
        return CurrentNumberOfHorses;
    }

    public void setCurrentNumberOfHorses(int currentNumberOfHorses) {
        CurrentNumberOfHorses = currentNumberOfHorses;
    }
}
