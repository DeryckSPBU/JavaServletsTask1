package com.epam.lab;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class SimpleServlet extends HttpServlet {

    static private List<String> list;

    @Override
    public void init() throws ServletException {
        System.out.println("I'm in initialization state!");
        list = new LinkedList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cookie = countCookie(req, resp);
        String userName = req.getParameter("userName").trim();
        if (!list.contains(userName)) {
            String content = "There is no such input";
            resp.setContentType("text/plain");
            resp.getWriter().print(content + "; views count: " + cookie);
        } else {
            resp.setContentType("text/plain");
            resp.getWriter().print(userName + "; views count: " + cookie);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cookie = countCookie(req, resp);
        String userName = req.getParameter("userName").trim();
        if ("".equals(userName)) {
            String content = "Input shouldn't be em";
            resp.setContentType("text/plain");
            resp.getWriter().print(content + "; views count: " + cookie);
        } else {
            String content = "Hi POST, " + userName;
            list.add(userName);
            resp.setContentType("text/plain");
            resp.getWriter().print(content + "; views count: " + cookie);
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cookie = countCookie(req, resp);
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String[] userName;
        String data = br.readLine();
        if (data != null) {
            userName = data.trim().split(" ");
        } else {
            userName = new String[]{""};
        }
        if (userName.length == 1) {
            if (userName[0].equals("")) {
                String content = "Input shouldn't be empty; views count: " + cookie;
                resp.setContentType("text/plain");
                resp.getWriter().print(content);
            } else {
                if (!list.contains(userName[0])) {
                    list.add(userName[0]);
                    resp.setContentType("text/plain");
                    resp.getWriter().print("Put " + userName[0] + "; views count: " + cookie);
                } else {
                    resp.setContentType("text/plain");
                    resp.getWriter().print("Cant edit to empty string; views count: " + cookie);
                }
            }
        } else {
            String old_name = userName[0];
            String new_name = userName[1];
            if (list.contains(old_name)) {
                if (new_name.equals("") || new_name.equals(old_name)) {
                    resp.setContentType("text/plain");
                    resp.getWriter().print("Cant edit; views count: " + cookie);
                } else {
                    list.remove(old_name);
                    list.add(new_name);
                    resp.setContentType("text/plain");
                    resp.getWriter().print(old_name + " edited to " + new_name + "; views count: " + cookie);
                }
            } else {
                if (new_name.equals("") || new_name.equals(old_name)) {
                    list.add(old_name);
                    resp.setContentType("text/plain");
                    resp.getWriter().print("Put " + old_name + "; views count: " + cookie);
                } else {
                    list.add(new_name);
                    resp.setContentType("text/plain");
                    resp.getWriter().print("Put " + new_name + "; views count: " + cookie);
                }
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cookie = countCookie(req, resp);
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String userName = br.readLine();
        if (userName != null) {
            userName = userName.trim();
        } else {
            userName = "";
        }
        if (list.contains(userName)) {
            list.remove(userName);
            resp.setContentType("text/plain");
            resp.getWriter().print("Deleted " + userName + "; views count: " + cookie);
        } else {
            String content = "There's no " + userName + "; views count: " + cookie;
            resp.setContentType("text/plain");
            resp.getWriter().print(content);
        }

    }

    @Override
    public void destroy() {
        System.out.println("Bye-bye.");
    }

    public String countCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        Cookie counterCookie = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("counter")) {
                counterCookie = cookie;
                break;
            }
        }

        if (counterCookie != null) {
            counterCookie.setValue(Integer.valueOf(counterCookie.getValue()) + 1 + "");
        } else {
            counterCookie = new Cookie("counter", "1");
        }
        resp.addCookie(counterCookie);
        return counterCookie.getValue();
    }
}
