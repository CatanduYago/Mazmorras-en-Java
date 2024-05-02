package com.dungeonmvc.interfaces;

public interface Observer {
    void onChange();
    void onChange(String... args);
}
