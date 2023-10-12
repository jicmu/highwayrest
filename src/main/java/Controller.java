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

    private final Map<String, Handler> pathList = new HashMap<String, Handler>();

    private Handler handler = null;

    public Controller() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();


        String packageName = "controller";

        String packageNameSlash = "./" + packageName.replace(".", "/");

        URL directoryURL = Thread.currentThread().getContextClassLoader().getResource(packageNameSlash);

        String directoryString = directoryURL.getFile();

        File file = new File(directoryString);
        if(file.exists()) {
            list(file.listFiles(), packageName);
        }
    }

    public void list(File[] fileList, String packageName) {
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
        handler = pathList.get(request.getRequestURI());
        String go = "";
        if(handler != null) {
            go = handler.doGet(request, response);

            if(go.startsWith("redirect")) {
                String path = go.replace("redirect/", "");
                response.sendRedirect(path);
            } else {
                RequestDispatcher dis = request.getRequestDispatcher(go);
                dis.forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handler = pathList.get(request.getRequestURI());
        System.out.println("dopost");
        String go = "";
        if(handler != null) {
            go = handler.doPost(request, response);
            System.out.println("inner");
            if(go.startsWith("redirect")) {
                System.out.println("redirect");
                String path = go.replace("redirect/", "");
                System.out.println("path=" + path);
                response.sendRedirect(request.getContextPath() + "/" + path);
            } else {
                RequestDispatcher dis = request.getRequestDispatcher(go);
                dis.forward(request, response);
            }
        }
    }
}
