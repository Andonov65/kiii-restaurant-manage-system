package com.finki.wp.ugostitelskiobjekti.web.controller.filter;


import com.finki.wp.ugostitelskiobjekti.model.User;
import org.springframework.context.annotation.Profile;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
@Profile("servlet")
//samo ako e aktiven profilot servlet
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //celoto filtriranje
        //SAKAME DA VIDIME DALI KORISNIKOT E AVTENTECIRAN
        //kje proverime dali ima istanca od user
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        User user= (User) request.getSession().getAttribute("user");
        //zemame info za user
        String path=request.getServletPath();
        //istrazhi go ifot
        //css go dozvolivme da bide javno
        if(!"/login".equals(path)&&!"/register".equals(path)&&!"/main.css".equals(path)&&user==null){
            //zabrana za pristap do dr -smee samo do login
            response.sendRedirect("/login");
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
