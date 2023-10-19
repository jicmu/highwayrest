import data.dto.RestReviewDTO;
import service.review.ReviewService;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
//        String path = "C:\\Users\\RYU\\Desktop\\project\\photo\\" + r.getMemberNo() + "\\" + r.getReviewNo();

        ReviewService service = new ReviewService();
        ArrayList<RestReviewDTO> list = service.getAll("000306");
        for(RestReviewDTO dto : list) {
            System.out.println(dto);
        }

//        MemberService memberService = new MemberService();
//        ArrayList<Member> mlist = memberService.getAll();
    }
}
