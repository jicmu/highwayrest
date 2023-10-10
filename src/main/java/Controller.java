import common.Handler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = { "/" })
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final Map<String, Handler> pathList = new HashMap<>();

    private Handler handler = null;

    public Controller() {
        super();
        System.out.println("Controller 생성 완료!");
    }

    @Override
    public void init() throws ServletException {
        super.init();

        String packageName = "controller";

        String packageNameSlash = "./" + packageName.replace(".", "/");
        System.out.println("packageName: " + packageNameSlash);

        URL directoryURL = Thread.currentThread().getContextClassLoader().getResource(packageNameSlash);

        String directoryString = directoryURL.getFile();

        File file = new File(directoryString);
        if(file.exists()) {
            list(file.listFiles(), packageName);
        }
    }

    public void list(File[] fileList, String packageName) {
        System.out.println("list[packageName]: " + packageName);
        for(File f : fileList) {
            if(f.getName().endsWith(".class")) {
                String fileName = f.getName();
                fileName = fileName.substring(0, fileName.length() - 6);
                try{
                    Class c = Class.forName(packageName + "." + fileName);
                    Constructor<?> constructor = c.getConstructor(null);
                    try {
                        Handler obj = (Handler)constructor.newInstance();
                        pathList.put(obj.getPath(), obj);
                        System.out.println("obj: " + obj);
                        System.out.println("getPath: " + pathList.get(obj.getPath()));
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                } catch (ClassNotFoundException | NoSuchMethodException e){
                    System.err.println(packageName + "." + fileName + " does not appear to be a valid class");
                    System.out.println(e.getMessage());
                }
            }
            else {
                String oPackageName = packageName;
                oPackageName += "." +f.getName();
                list(f.listFiles(), oPackageName);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet 실행됨");
        String uri = request.getRequestURI();
        handler = pathList.get(uri);
        String go = "";
        if(handler != null) {
            go = handler.doGet(request, response);
            System.out.println("go: " + go);

            if(go.startsWith("redirect")) {
                String path = go.split("/")[1];

                path = go.replace("redirect/", "");

                response.sendRedirect(path);
            } else if (go.startsWith("responsebody")) {
                String[] path = go.split("/");
                response.getWriter().append(path[1]);
            } else {
                System.out.println("실행됨");
                RequestDispatcher dis = request.getRequestDispatcher(go);
                dis.forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost 실행됨");
        String uri = request.getRequestURI();
        handler = pathList.get(uri);
        String go = "";
        if(handler != null) {
            go = handler.doPost(request, response);
            System.out.println("go: " + go);

            if(go.startsWith("redirect")) {
                String path = go.split("/")[1];
                response.sendRedirect(path);
            } else if (go.startsWith("responsebody")) {
                String[] path = go.split("/");
                response.getWriter().append(path[1]);
            } else {
                System.out.println("실행됨");
                RequestDispatcher dis = request.getRequestDispatcher(go);
                dis.forward(request, response);
            }
        }
    }
}