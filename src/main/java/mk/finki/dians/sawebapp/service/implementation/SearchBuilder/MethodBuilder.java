package mk.finki.dians.sawebapp.service.implementation.SearchBuilder;

import java.lang.reflect.InvocationTargetException;

public abstract class MethodBuilder {
    protected Class className;
    protected String method;
    
    public abstract Object call(Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    
    
    public abstract MethodBuilder setParameter(String parameterName, Object value, Class c) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    
    /**
     * Sets the method to be used by the Builder when calling a function.
     * @param method the method name.
     * @return SearchConditionBuilder the object that called this function.
     */
    public MethodBuilder setSearchMethod(String method){
        this.method = method;
        return this;
    }
}
