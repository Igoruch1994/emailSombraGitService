package servlet;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/identifyUser")
public class IdentifyUserServlet extends HttpServlet {

    static final long serialVersionUID = 02L;

    @Autowired
    UserService userService;

    ApplicationContext applicationContext = null;

    @Override
    public void init() throws ServletException {
        super.init();
        applicationContext = ApplicationContextProvider.getApplicationContext();
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = new Gson().toJson(userService.identifyUser());
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
