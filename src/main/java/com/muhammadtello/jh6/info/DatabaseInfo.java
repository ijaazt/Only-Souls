package com.muhammadtello.jh6.info;

public enum DatabaseInfo {
    URL("jdbc:mysql://localhost:3306/root?serverTimezone=UTC"),
    USERNAME("root"),
    PASSWORD("En7j6pur8v"),
    DRIVER_CLASS_NAME("com.mysql.cj.jdbc.Driver");

    public String info() {
        return info;
    }
    private String info;

    private DatabaseInfo(String info) {
        this.info = info;
    }
}
