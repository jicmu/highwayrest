import common.FileSearch;
import common.Handler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        FileSearch.fileSearchAndConstructor("controller", pathList);
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
            } else if(go.startsWith("responsebody")) {
                String[] path = go.split("/");
                response.getWriter().append(path[1]);//{flag:true}
            } else if(go.startsWith("response/")) {
                System.out.println("go: " + go);
            } else if(go == null) {
                System.out.println("restapi: " + go);
            } else {
                RequestDispatcher dis = request.getRequestDispatcher(go);
                dis.forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handler = pathList.get(request.getRequestURI());
        String go = "";
        if(handler != null) {
            go = handler.doPost(request, response);

            if(go.startsWith("redirect")) {
                String path = go.replace("redirect/", "");
                response.sendRedirect(request.getContextPath() + "/" + path);
            } else if (go.startsWith("responsebody")) {
                String[] path = go.split("/");
                response.getWriter().append(path[1]);//{flag:true}
            } else if(go.startsWith("response/")) {
                System.out.println("go: " + go);
            } else {
                RequestDispatcher dis = request.getRequestDispatcher(go);
                dis.forward(request, response);
            }
        }
    }
}
