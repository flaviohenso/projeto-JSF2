/**
 * projeto-web : 11 de ago de 2017 
 */
package com.flavio.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flavio.service.ProdutoService;
import com.google.gson.Gson;

/**
 * @author flavio
 *
 */
@WebServlet(name="autoCompleteProduto", urlPatterns="/compras/autoCompleteProduto")
public class ProdutoComplete extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoService produtoService;
	
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

            response.setContentType("application/json");
            try {
                    String term = request.getParameter("query");
                    System.out.println("Data from ajax call ");
                  
                    ArrayList<String> list =  produtoService.autoComplete(term);

                    String searchList = new Gson().toJson(list);
                    response.getWriter().write(searchList);
            } catch (Exception e) {
                    System.err.println(e.getMessage());
            }
    }

}
