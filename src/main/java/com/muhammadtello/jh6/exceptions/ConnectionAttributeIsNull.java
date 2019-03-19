package com.muhammadtello.jh6.exceptions;

import javax.servlet.http.HttpServlet;

public class ConnectionAttributeIsNull extends Exception {
    public ConnectionAttributeIsNull(String attribute) {
        super(attribute);
    }
}
