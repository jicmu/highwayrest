package listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private int cnt = 0;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        cnt++;
        System.out.println("세션 생성!");
        System.out.println("session: " + se.getSession());
        System.out.println("cnt: " + cnt);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        cnt--;
        System.out.println("세션 파괴");
        System.out.println("cnt: " + cnt);
    }
}
