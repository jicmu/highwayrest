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
        doPost(request, response);

        return "/payment/order.jsp";
    }

    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stdRestCd = request.getParameter("stdRestCd");
        String[] foodNos = request.getParameterValues("foodNo");
        String[] foodCosts = request.getParameterValues("foodCost");
        String[] amounts = request.getParameterValues("amount");

        int max = 10;
        if (amounts != null && amounts.length != 0) {
            for (String a : amounts) {
                int amount = Integer.parseInt(a);

                if (max < amount) {
                    max = amount;
                }
            }
        }

        request.setAttribute("max", max);

        if (foodNos == null || foodCosts == null || amounts == null
                || foodNos.length == 0 || foodCosts.length == 0 || amounts.length == 0) {
            return "/payment/order.jsp";
        }

        List<OrderParamDTO> dtos = new ArrayList<>();
        for (int i = 0; i < foodNos.length; i++) {
            OrderParamDTO orderParamDTO = OrderParamDTO.builder()
                    .stdRestCd(stdRestCd)
                    .foodNo(Integer.parseInt(foodNos[i]))
                    .foodNm(service.getMenuByFoodNo(Integer.parseInt(foodNos[i])))
                    .foodCost(Integer.parseInt(foodCosts[i]))
                    .amount(Integer.parseInt(amounts[i]))
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
