package wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class Wrapper {
    Map<String, Object> attributes = new HashMap();
    Map<String, Object> sessionParameters = new HashMap();

    public void extract(HttpServletRequest request){
        Enumeration attributes = request.getAttributeNames();
        String name;

        while(attributes.hasMoreElements()){
            name = (String)attributes.nextElement();
            this.attributes.put(name,request.getAttribute(name));
        }

        HttpSession session = request.getSession();
        attributes = session.getAttributeNames();

        while(attributes.hasMoreElements()){
            name = (String)attributes.nextElement();
            this.sessionParameters.put(name,request.getAttribute(name));
        }
    }

    public void setRequestAttribute(String name, Object value) {
        if(attributes.containsKey(name)){
            attributes.put(name, value);
        }
    }

    public Object getRequestAttribute(String name){
        if(attributes.containsKey(name)){
            return attributes.get(name);
        }
//        FIX IT!!!
        throw new IllegalArgumentException();
    }

    public void setSessionAttribute(String name, Object value){
        if(sessionParameters.containsKey(name)){
            sessionParameters.put(name, value);
        }
    }

    public Object getSessionAttribute(String name){
        if(sessionParameters.containsKey(name)){
            return sessionParameters.get(name);
        }
//        FIX IT!!!
        throw new IllegalArgumentException();
    }

    public void updateRequest(HttpServletRequest request){
        for(String name: attributes.keySet()){
            request.setAttribute(name,attributes.get(name));
        }

        HttpSession session = request.getSession();

        for(String name: sessionParameters.keySet()){
            session.setAttribute(name, sessionParameters.get(name));
        }
    }
}
