package model;

public class Soldier extends People {

    //todo to add a kingdom of kingdom
    private Empire owner;

    public Empire getOwner() {
        return owner;
    }

    public void setOwner(Empire owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    private  String name;
    private SoldierType soldierType;
    private int situation;
    private boolean stop;
    private boolean patrol;
    private boolean stopPatrol;

    private int totalMove;
    private int patrol_x_start;
    private int patrol_y_start;
    private int patrol_x_end;
    private int patrol_y_end;
    private int x;
    private int y;

    public SoldierType getSoldierType() {
        return soldierType;
    }

    public Soldier(int hp, String name, SoldierType soldierType, Empire owner, int x, int y) {
        super(hp);
        this.name = name;
        this.soldierType = soldierType;
        this.x = x;
        this.y = y;
        this.situation = 2;
        this.owner = owner;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setTotalMove(int totalMove) {
        this.totalMove += totalMove;
    }

    public void reSetTotalMove(int reset) {
        this.totalMove = 0;
    }

    public int getTotalMove() {
        return totalMove;
    }

    public int getSituation() {
        return situation;
    }

    public void setSituation(int situation) {
        this.situation = situation;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean isPatrol() {
        return patrol;
    }

    public void setPatrol(boolean patrol) {
        this.patrol = patrol;
    }

    public int getPatrol_x_start() {
        return patrol_x_start;
    }

    public void setPatrol_x_start(int patrol_x_start) {
        this.patrol_x_start = patrol_x_start;
    }

    public int getPatrol_y_start() {
        return patrol_y_start;
    }

    public void setPatrol_y_start(int patrol_y_start) {
        this.patrol_y_start = patrol_y_start;
    }

    public int getPatrol_x_end() {
        return patrol_x_end;
    }

    public void setPatrol_x_end(int patrol_x_end) {
        this.patrol_x_end = patrol_x_end;
    }

    public int getPatrol_y_end() {
        return patrol_y_end;
    }

    public void setPatrol_y_end(int patrol_y_end) {
        this.patrol_y_end = patrol_y_end;
    }

    public boolean isStopPatrol() {
        return stopPatrol;
    }

    public void setStopPatrol(boolean stopPatrol) {
        this.stopPatrol = stopPatrol;
    }
}
