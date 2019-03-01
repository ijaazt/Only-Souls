package com.muhammadtello.jh6.beans;

import javax.servlet.http.HttpServletRequest;

public class ModelParameters {
    private Person person;

    public ModelParameters(HttpServletRequest request) throws Exception {
        String firstName = getParameter(request, "firstName");
        String lastName = getParameter(request, "lastName");
        String eyeColor = getParameter(request,"eyeColor");
        String hairColor = getParameter(request,"hairColor");
        int heightFt = Integer.parseInt(getParameter(request, "heightFt"));
        int heightInchs = Integer.parseInt(getParameter(request, "heightFt"));
        double weight = Double.parseDouble(getParameter(request, "weight"));
        int id = Integer.parseInt(getParameter(request, "id"));
        person = new Person(firstName, lastName, eyeColor, hairColor, new Size(heightFt, heightInchs), weight, id);
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

    public void setPerson(Person person) {
        this.person = person;
    }
}
