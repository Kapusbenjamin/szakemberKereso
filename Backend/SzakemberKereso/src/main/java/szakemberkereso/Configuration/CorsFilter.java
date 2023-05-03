/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
            ContainerResponseContext responseContext) throws IOException {
        ArrayList<String> urls = new ArrayList();
        urls.add("http://localhost:4200");
        urls.add("http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Users/");
//          responseContext.getHeaders().add(
//            "Access-Control-Allow-Origin", "*");
        String url = requestContext.getHeaderString("Origin");
        if (urls.contains(url)) {
            responseContext.getHeaders().add(
                    "Access-Control-Allow-Origin", url);
        }

        responseContext.getHeaders().add(
                "Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization, token, x-requested-with, cache-control, Pragma");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }

}
