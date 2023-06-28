package online.shop.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ServletExController {

    @GetMapping("/error-ex")
    public void errorEx(){
        throw new RuntimeException("예외 발생!");
    }

    @GetMapping("/error-404")
    public void error404(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendError(HttpStatus.NOT_FOUND.value(), "404 오류!");
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "500 오류!");
    }
}
