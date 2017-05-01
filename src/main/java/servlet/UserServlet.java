package servlet;

import com.google.gson.Gson;
import model.User;
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

@WebServlet("/user")
public class UserServlet extends HttpServlet {

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

        if (req.getParameter("id")==null){
            String json = new Gson().toJson(userService.getAllUser());
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        }
        else {
            Integer userId = Integer.parseInt(req.getParameter("id"));
            String json = new Gson().toJson(userService.getUserById(userId));
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new Gson().fromJson(req.getReader(), User.class);
        userService.add(user);
        resp.getWriter().print("Succsess");
    }
}
