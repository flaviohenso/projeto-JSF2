/**
 * projeto-web : 10 de ago de 2017 
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

import com.flavio.service.FornecedorService;
import com.google.gson.Gson;

/**
 * @author flavio
 *
 */
@WebServlet(name="autoCompleteFornecedor", urlPatterns="/compras/autoCompleteFornecedor")
public class AutoComplete extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FornecedorService fornecedorService;
	
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

            response.setContentType("application/json");
            try {
                    String term = request.getParameter("query");
                    System.out.println("Data from ajax call ");
                  
                    ArrayList<String> list =  fornecedorService.autoComplete(term);

                    String searchList = new Gson().toJson(list);
                    response.getWriter().write(searchList);
            } catch (Exception e) {
                    System.err.println(e.getMessage());
            }
    }

}
