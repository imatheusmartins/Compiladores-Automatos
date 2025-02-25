import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ValidationController {

    @PostMapping("/validar")
    public String validarCpf(@RequestParam("cpf") String cpf, Model model) {
        String mensagem = Utils.validarCpf(cpf);
        model.addAttribute("mensagem", mensagem);
        return "index";
    }
}
