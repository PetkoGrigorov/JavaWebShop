
import framework.annotation.Authenticated;
import framework.annotation.MVCRoute;
import model.system.AuthUser;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@WebServlet(value = "/base/*")
public class FrontController extends HttpServlet {

    /*private void requestProcessor(String methodType, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        String[] pathArr = pathInfo.substring(1).split("/");
        String controllerClassId = getController(pathArr);
        String controllerMethodId = getMethod(pathArr);

        System.out.println("+++++++++++++++++++++++++++++++++");
        System.out.println("pathInfo: " + pathInfo);
        System.out.println("QueryString: " + req.getQueryString());
        System.out.println("methodType: " + methodType);
        System.out.println("---------------------------------");


        try {
            Class<?> controllerClass = Class.forName(controllerClassId);
            Object controllerInstance = controllerClass.newInstance();

            Method[] methodCollection = controllerClass.getDeclaredMethods();
            String correctControllerMethodId = this.getCorrectMethodName(methodCollection, methodType, controllerMethodId);
            System.out.println("Correct method name: " + correctControllerMethodId);


            Method controllerMethod = controllerClass.getDeclaredMethod(correctControllerMethodId, HttpServletRequest.class, HttpServletResponse.class);
            System.out.println("controllerMethod: " + controllerMethod);

            controllerMethod.invoke(controllerInstance, req, resp);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            PageNotFoundController.index(req, resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            PageNotFoundController.index(req, resp);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }*/

    private void requestProcessorAnnotation(String methodType, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        String[] pathArr = pathInfo.substring(1).split("/");
        String  controllerClassId = getControllerClassName(pathArr[0]);
        String controllerMethodId = getMethod(pathArr);

        System.out.println("+++++++++++++++++++++++++++++++++");
        System.out.println("pathInfo: " + pathInfo);
        System.out.println("Class: " + controllerClassId);
        System.out.println("Method: " + controllerMethodId);
        System.out.println("methodType: " + methodType);
        System.out.println("---------------------------------");


//        ================================================================================
        try {
            Class<?> classReference = null;
            try {
                classReference = Class.forName(controllerClassId);
            } catch (ClassNotFoundException e) {
                classReference = Class.forName("controller.PageNotFoundController");
            }
            Object classInstance = classReference.newInstance();
            Method[] methodCollection = classReference.getDeclaredMethods();
            Method classMethod = null;
            for (Method method : methodCollection) {
                if (method.isAnnotationPresent(MVCRoute.class)) {
                    MVCRoute annotation = method.getAnnotation(MVCRoute.class);
                    if (annotation.path().equals(pathInfo) && annotation.method().equals(methodType)) {
                        classMethod = method;
                        if (method.isAnnotationPresent(Authenticated.class)) {
//                            Authenticated authAnnotation = method.getAnnotation(Authenticated.class);
                            if (!AuthUser.isUserAuthenticated()) {
                                invokeControllerMethod("auth", "login", req, resp);
                                return;
                            }
                        }
                        break;
                    }
                }
            }
            if (classMethod == null) {
                classMethod = classReference.getDeclaredMethod("index", HttpServletRequest.class, HttpServletResponse.class);
            }
            System.out.println("Method to invoke: " + classMethod.getName());
            System.out.println("Object for the method: " + classInstance);
            classMethod.invoke(classInstance, req, resp);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
//        ===================================================================================

//        try {
//            Class<?> controllerClass = Class.forName(controllerClassId);
//            Object controllerInstance = controllerClass.newInstance();
//
//            Method[] methodCollection = controllerClass.getDeclaredMethods();
//            String correctControllerMethodId = this.getCorrectMethodName(methodCollection, methodType, controllerMethodId);
//            System.out.println("Correct method name: " + correctControllerMethodId);
//
//
//            Method controllerMethod = controllerClass.getDeclaredMethod(correctControllerMethodId, HttpServletRequest.class, HttpServletResponse.class);
//            System.out.println("controllerMethod: " + controllerMethod);
//
//            controllerMethod.invoke(controllerInstance, req, resp);
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            PageNotFoundController.index(req, resp);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//            PageNotFoundController.index(req, resp);
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

    }

    private String getControllerClassName(String className) {
        try {
            String controller = "controller." + className.substring(0, 1).toUpperCase() + className.substring(1) + "Controller";
            return controller;
        } catch (Exception e) {
            return "controller.HomeController";
        }
    }

    private String getMethod(String[] pathArr) {
        try {
            String method = pathArr[1];
            return method;
        } catch (Exception e) {
            return "index";
        }
    }

    private String getCorrectMethodName(Method[] methodCollection, String methodType, String methodId) {
        for (Method classMethod : methodCollection) {
            if (methodType.equals("POST") && classMethod.getName().equals("post_" + methodId)) {
                return classMethod.getName();
            }
            if (methodType.equals("GET") && (classMethod.getName().equals("get_" + methodId) || classMethod.getName().equals(methodId))) {
                return classMethod.getName();
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        requestProcessor("GET", req, resp);

        requestProcessorAnnotation("GET", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        requestProcessor("POST", req, resp);

        requestProcessorAnnotation("POST", req, resp);
    }

    private void invokeControllerMethod(String controllerPathName, String methodPathName, HttpServletRequest req, HttpServletResponse resp) {
        try {
            Class<?> controllerReference = Class.forName(getControllerClassName(controllerPathName));
            Object controllerInstance = controllerReference.newInstance();
            Method[] allMethods = controllerReference.getDeclaredMethods();
            for (Method method : allMethods) {
                if (method.getName().equals(methodPathName)) {
                    method.invoke(controllerInstance, req, resp);
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static void redirectTo() {

    }

}
