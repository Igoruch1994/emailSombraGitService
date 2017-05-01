package servlet;

import com.google.gson.Gson;
import model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/message")
public class MessageServlet extends HttpServlet {

    @Autowired
    MessageService messageService;

    ApplicationContext applicationContext = null;

    @Override
    public void init() throws ServletException {
        super.init();
        applicationContext = ApplicationContextProvider.getApplicationContext();
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = new Gson().toJson(messageService.getAllMessageForCurrentUser());
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if (email!=null||!email.equals("undefined")) {
            Message message = new Gson().fromJson(req.getReader(), Message.class);
            messageService.add(message, email);
        }
    }
}
