package com.example.auth_service.filters;


import com.example.auth_service.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

//    private final UserDetailsService userDetailsService;



    // autowire jwtService
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

//    private final RequestMatcher uriMatcher =
//            request -> "/api/v1/auth/signin/*".equals(request.getRequestURI()) && HttpMethod.POST.matches(request.getMethod());

    //            AntPathRequestMatcher("/api/v1/auth/signin/*" ,HttpMethod.POST.name());

//    private final RequestMatcher uriMatcher = request ->
//            HttpMethod.POST.matches(request.getMethod()) &&
//                    request.getRequestURI().startsWith("/api/v1/auth/validate/*");


    private final RequestMatcher uriMatcher =
            new RegexRequestMatcher("^/api/v1/auth/validate/.*$", HttpMethod.POST.name());

    public JwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        if(request.getCookies()!=null){
            for(Cookie cookie :request.getCookies()){
               if(cookie.getName().equals("JwtToken")){
                   token = cookie.getValue();
               }
            }
        }

//        if(token==null){
//            // user has not provided jwt token thus request should not go forward .....
//             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             return;
//        }
        System.out.println("woking mahiol" + token);
        String email = jwtService.extractEmail(token);
        System.out.println("---->" + email);

        if(email!=null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            if(jwtService.validateToken(token , userDetails.getUsername())){       //we had username as email only .....
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails , null);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);  //make sure spring should store this automatically -> otherwise we have to implement dto manually
            }
        }
        System.out.println("passed------>>>" + request.getRequestURI());

        //usernamePasswordAuthenticationToken -> we can validate token and role of user as well easily using this ...
        //webauthdetser -> covert req to springboot compatible req ..
        filterChain.doFilter(request , response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)  {
        RequestMatcher matcher = new NegatedRequestMatcher(uriMatcher) ;

        System.out.println("hii->" + request);
//        return uriMatcher.matches(request);
        return matcher.matches(request);
//            return false;
        // for  validate we are making NEGATE_MATCHER
        //
    }
}
