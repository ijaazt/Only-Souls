package com.muhammadtello.jh6.beans;

import javax.servlet.http.HttpServletRequest;

public class ModelParameters {
    private Person person;
    private Method _method;

    public ModelParameters(HttpServletRequest request) throws Exception {
        String firstName = getParameter(request, "firstName");
        String lastName = getParameter(request, "lastName");
        String eyeColor = getParameter(request,"eyeColor");
        String hairColor = getParameter(request,"hairColor");
        int heightFt = Integer.parseInt(getParameter(request, "heightFt"));
        int heightInch = Integer.parseInt(getParameter(request, "heightInch"));
        double weight = Double.parseDouble(getParameter(request, "weight"));
        int id = Integer.parseInt(getParameter(request, "id"));
        _method = Method.valueOf(getParameter(request, "_method").toUpperCase().trim());
        Size height = new Size(heightFt, heightInch);
        person = new Person(firstName, lastName, eyeColor, hairColor, height, weight, id);
    }

    private String getParameter(HttpServletRequest request, String paramName) throws NullParameterException, NullRequestParameterException {
        if(paramName == null) throw new NullParameterException("parameter paramName is null");
        String paramValue = request.getParameter(paramName);
        if(paramValue == null) {
            throw new NullRequestParameterException(paramName);
        }
        return paramValue;
    }

    public Person getPerson() {
        return person;
    }

    public Method getMethod() {
        return _method;
    }

}
