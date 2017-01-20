package sec.project.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address) {
        if (name.isEmpty() || address.isEmpty()) {
            return "redirect:/form";
        }
        signupRepository.save(new Signup(name, address));
        return "redirect:/done";
    }
    @RequestMapping(value="/done")
    public String done(Model model) {
        long i = signupRepository.count();
        List<Signup> list = new ArrayList<>();
        list = signupRepository.findAll();
        model.addAttribute("people", list);
        return "done";
    }

}