import java.lang.reflect.*;

public class FindApp {
    public static void main(String[] arguments)  {
        if (arguments.length == 1) {
            Class inspect;
            try {
                boolean isApplication = false;
                inspect = Class.forName(arguments[0]);
                Method[] methods = inspect.getDeclaredMethods();
                for (int i = 0; i < methods.length; i++) {
                    String methName = methods[i].getName();
                    if (methName.equals("main")) {
                        Class[] paramVal = methods[i].getParameterTypes();
                        int modVal = methods[i].getModifiers();
                        Class returnVal = methods[i].getReturnType();
                        String returnName = returnVal.getName();
                        StringBuffer params = new StringBuffer();
                        for (int j = 0; j < paramVal.length; j++) {
                            if (j > 0)
                                params.append(", ");
                            params.append(paramVal[j].getName());
                        }
                        String paramName = params.toString();
                        if (Modifier.isPublic(modVal))
                            if (Modifier.isStatic(modVal))
                                if (returnName.equals("void"))
                                    if (paramName.equals("[Ljava.lang.String;"))
                                        isApplication = true;
                    }
                }
                if (isApplication)
                    System.out.println(arguments[0] + ".class is an application.");
                else
                    System.out.println(arguments[0] + ".class is not an application.");
            } catch (ClassNotFoundException c) {
                System.out.println(c.toString());
            }
        }
    }
}

