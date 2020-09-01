package com.clickcharm.transactoinapi.filters;

import com.clickcharm.transactoinapi.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String authHeader = httpServletRequest.getHeader("Authorization");

        if(authHeader != null ){
            String[] authHeaderList = authHeader.split("Bearer ");
            if(authHeaderList.length > 1 && authHeaderList[1] != null){
                String token = authHeaderList[1];
                try{
                    Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY)
                            .parseClaimsJws(token).getBody();
                    int userid=Integer.parseInt(claims.get("userId").toString());
                    System.out.println("user logged : "+userid);
                    httpServletRequest.setAttribute("userId", userid);
                }catch (Exception e){
                    httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(),"Invalid/Expired token");
                    return;
                }
            }else {
                httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be Bearer [token]");
                return;
            }
        }else{
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be provided");
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
