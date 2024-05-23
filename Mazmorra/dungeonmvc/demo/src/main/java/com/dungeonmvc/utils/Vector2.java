package com.dungeonmvc.utils;

public class Vector2 {
    private int x;
    private int y;
    public Vector2(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double distance(Vector2 other){
        return Math.sqrt(Math.pow(x-other.getX(),2)+Math.pow(y - other.getY(),2));

    }

    @Override
    public String toString() {
        return "{" +
            " x='" + getX() + "'" +
            ", y='" + getY() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vector2 other = (Vector2) obj;
        return x == other.x && y == other.y;
    }
    public static void main(String[] args) {
        Vector2 v1 = new Vector2(3, 6);
        Vector2 v2 = new Vector2(4, 7);
        System.out.println(v1.distance(v2));
    }
}
