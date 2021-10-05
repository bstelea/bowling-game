import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Player implements Comparable<Player>{
    private int id, strikeRecord, spareRecord;
    private List<Integer> points;
    private int score;
    private boolean isHuman;

    public Player(int id, boolean isHuman) {
        this.id = id;
        this.points = new ArrayList<>();
        this.isHuman = isHuman;
        this.score = 0;
        this.strikeRecord = 0;
        this.spareRecord = 0;
    }

    public int getId() {
        return this.id;
    }

    public void addPoint(Integer point) {
        this.points.add(point);
        score += point;
    }

    public void addScore(Integer score) {
        this.score += score;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public int getScore() {
        return this.score;
    }

    public void incrementStrikeRecord() {
        this.strikeRecord++;
    }

    public void decrementStrikeRecord() {
        this.strikeRecord--;
    }

    public void incrementSpareRecord() {
        this.spareRecord++;
    }

    public void decrementSpareRecord() {
        this.spareRecord--;
    }

    public int getStrikeRecord() {
        return this.strikeRecord;
    }

    public int getSpareRecord() {
        return this.spareRecord;
    }

    public List<Integer> getPoints() {
        return this.points;
    }

    @Override
    public int compareTo(Player o) {
        return Integer.compare(score, o.score);
    }
}
