package beans;

import javax.servlet.http.HttpServletRequest;

interface Converter<T, O> {

}

public class ModelParameters {
    Person person;
    String _method;

    public ModelParameters(HttpServletRequest request) throws Exception {
        String firstName = getParameter(request, "firstName");
        String lastName = getParameter(request, "lastName");
        String eyeColor = getParameter(request,"eyeColor");
        String hairColor = getParameter(request,"hairColor");
//        Integer heightFt = getParameter(request, "heightFt");
//        Integer heightIn = getParameter(request, "heightIn");
        Integer height = Integer.parseInt(getParameter(request, "height"));
        String weight = getParameter(request, "weight");
        _method = getParameter(request,"_method");
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

    public String getMethod() {
        return _method;
    }
}
