package br.com.fiap.tds2.fase1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.fiap.tds2.fase1.business.LoginFacade;
import br.com.fiap.tds2.fase1.exceptions.LoginAtivoException;
import br.com.fiap.tds2.fase1.exceptions.LoginExcedeuTentativasException;
import br.com.fiap.tds2.fase1.exceptions.LoginExpiradoException;
import br.com.fiap.tds2.fase1.exceptions.LoginInvalidoException;
import br.com.fiap.tds2.fase1.exceptions.LoginIpInvalidoException;
import br.com.fiap.tds2.fase1.model.UsuarioModel;

@SuppressWarnings("serial")
@WebServlet("/Login")
public class LoginController extends HttpServlet {
	

	private static final Logger LOGGER = LogManager.getLogger(LoginController.class.getName());
	private final LoginFacade loginFacade = new LoginFacade();
	
	public LoginController() {
        super();
    }

    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try { 
//			LOGGER.debug("Executando o Servlet LoginController");
			
			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setLogin(request.getParameter("login"));
			usuarioModel.setSenha(request.getParameter("senha"));
			
			loginFacade.loginWeb(usuarioModel);
			
			RequestDispatcher despachar = request.getRequestDispatcher("home.jsp");
			despachar.forward(request, response);
			
// 			LOGGER.debug("Recuperando os dados do login");
//			
//			LoginBusiness.validarUsuarioAtivo(usuarioModel);
//			LOGGER.info("Validando dados Usuario");
//			
//			LoginBusiness.validarTentativasdeLogin(usuarioModel);
//			LOGGER.info("Validando tentativas");
//			
//			LoginBusiness.validarUsuarioExpirado(usuarioModel);
//			LOGGER.info("Validando Usuario Expirado");
//			
//			LoginBusiness.validarIp(usuarioModel);
//			System.out.println("Validando Ip");
//			
//			LoginBusiness.validarLogin(usuarioModel);
//			LOGGER.info("Validando Login e Senha");
			
			
		} catch ( LoginAtivoException | LoginExcedeuTentativasException | LoginExpiradoException| LoginInvalidoException | LoginIpInvalidoException  e) {
			LOGGER.error("Erro ");
			response.sendRedirect("erro.jsp?msg=" + e.getMessage());
	    } catch (Exception e) {
	    	LOGGER.error("Ocorreu um erro muito cr�tico, desconhecido pela aplica��o");
			response.sendRedirect("erro.jsp?msg=Erro cr�tico");
		}
	}



	
}
