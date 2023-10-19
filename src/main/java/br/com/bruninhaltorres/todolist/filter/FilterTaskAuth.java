package br.com.bruninhaltorres.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.bruninhaltorres.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // Classe genérica do gerenciamento do Sprint
public class FilterTaskAuth extends OncePerRequestFilter{ // Assim não vai ser necessário converter ServletRequest em HttpServletRequest.

    @Autowired
    private IUserRepository userRepository;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {

        var serveletPath = request.getServletPath();

        // Permite que essa validação só seja feita na rota tasks e não em todas as rotas.
        if(serveletPath.startsWith("/tasks/")){

            // Pegar a autenticação (usuário e senha)
            var authorization = request.getHeader("Authorization");
            // System.out.println(authorization);
            // Output = Basic 2IDWBONDSIIJ
            
            // Separa a palavra da senha que precisamos.
            var authEncoded = authorization.substring("Basic".length()).trim(); // trim: remove os espaços em branco
            // System.out.println(authEncoded);
            // Output = 2IDWBONDSIIJ
            
            // Decodificação da senha
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            // System.out.println(authDecode);
            // Output = Array de bytes
            
            // Converter os bytes em string
            var authString = new String(authDecode);
            // System.out.println(authString);
            // Output = bruninhaltorres:12345
            
            // Separa só a senha e o retorno dessa função é um array
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];
            
            // System.out.println(username);
            // System.out.println(password);

            // Validar usuário
            var user = this.userRepository.findByUsername(username);

            if (user == null){
                response.sendError(401);
                // System.out.println("aqui");
            } else {
                // Validar senha
                // System.out.println(user.getPassword());
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                
                if(passwordVerify.verified){
                    // Segue
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }

    }

}
