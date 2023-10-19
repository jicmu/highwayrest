package controller.master;

import common.Handler;
import data.dto.RestMasterStatusDTO;
import data.entity.HighwayRest;
import data.entity.OrderList;
import orders.Order;
import orders.OrderService;
import service.master.HighwayRestService;
import service.master.RestMasterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MasterLogin implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HighwayRestService service = new HighwayRestService();
        OrderService orderService = new OrderService();
        ArrayList<HighwayRest> list = service.getAll();

        request.setAttribute("list", list);
        for(HighwayRest rest : list) {
            System.out.println("rest: " + rest);
        }

        RestMasterService restMasterService = new RestMasterService();
        ArrayList<RestMasterStatusDTO> restmasterlist = restMasterService.getAllMaster();
        ArrayList<OrderList> orderLists = new ArrayList<>();
        for(RestMasterStatusDTO dto : restmasterlist) {
            if(dto.getStatus() == 1) {
                HttpSession session = request.getSession();
                session.setAttribute(dto.getId(), dto.getId());

                System.out.println("sessionId:" + session.getAttribute(dto.getId()));

//                List<Order> orderList = orderService.findByRestNo(dto.getId());

//                request.setAttribute("loginStore", dto.getId());
//
//                request.setAttribute("orderList", orderList);

//                OrderList ol = new OrderList();
//                ol.setId(dto.getId());
//                ol.setList(orderList);
//                orderLists.add(ol);
            }
        }
//        request.setAttribute("view", "/store/order.jsp");
//        request.setAttribute("orderLists", orderLists);
        request.setAttribute("restmasterlist", restmasterlist);

        return "/master/login.jsp";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        return "";
    }

    @Override
    public String getPath() {
        return path + "/master/login";
    }
}
