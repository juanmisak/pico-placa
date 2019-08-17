package com.example.picoyplaca.models;

public class ItemHistoryObject {
    public final String plate;
    public final String timestamp;
    public final int senior_citizen;
    public final int handicapped;

    public ItemHistoryObject(String plate, String timestamp, int senior_citizen, int handicapped) {
        this.plate = plate;
        this.timestamp = timestamp;
        this.senior_citizen = senior_citizen;
        this.handicapped = handicapped;
    }

    public String getPlate() {
        return plate;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int isSenior_citizen() {
        return senior_citizen;
    }

    public int isHandicapped() {
        return handicapped;
    }

    @Override
    public String toString() {
        return plate;
    }
}