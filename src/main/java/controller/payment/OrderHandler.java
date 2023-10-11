package controller.payment;

import common.Handler;
import data.dto.OrderParamDTO;
import orders.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderHandler implements Handler {

    private OrderService service;

    public OrderHandler() {
        System.out.println("Order 생성!");

        service = new OrderService();
    }
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/payment/order.jsp";
    }

    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] stdRestCds = request.getParameterValues("stdRestCd");
        String[] foodNms = request.getParameterValues("foodNm");
        String[] foodCosts = request.getParameterValues("foodCost");

        List<OrderParamDTO> dtos = new ArrayList<>();
        for (int i = 0; i < foodNms.length; i++) {
            OrderParamDTO orderParamDTO = OrderParamDTO.builder()
                    .stdRestCd(stdRestCds[i])
                    .foodNm(foodNms[i])
                    .foodCost(Integer.parseInt(foodCosts[i]))
                    .build();

            dtos.add(orderParamDTO);
        }

        request.setAttribute("foodList", dtos);

        return "/payment/order.jsp";
    }

    public String getPath() {
        return path + "/order";
    }
}
