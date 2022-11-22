package by.htp.ex.controller;

import by.htp.ex.entity.UserInfo;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final String userInfoAttribute = "newUserInfo";
    private static final String regResultMessageAttribute = "regMessage";
    private static final String messageLoginExistAttribute = "messageLoginExist";
    private static final String registrationAttribute = "reg";
    private static final String regStatus = "active";
    private static final String regResultMessage = "Successful registration!";
    @Autowired
    private UserService service;

    @RequestMapping("/showForm")
    public String showForm(@ModelAttribute(userInfoAttribute) UserInfo newUserInfo, Model model) {
        model.addAttribute(registrationAttribute, regStatus);
        return "baseLayout";
    }

    @RequestMapping("/do_registration")
    public String doRegistration(@ModelAttribute(userInfoAttribute) @Valid UserInfo newUserInfo,
                                 BindingResult bindingResult, RedirectAttributes attr, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(registrationAttribute, regStatus);
            return "baseLayout";
        }
        String password = newUserInfo.getPassword();
        String springPassword = "{noop}" + password;
        newUserInfo.setPassword(springPassword);
        try {
            boolean result = service.registration(newUserInfo);
            if (result) {
                attr.addAttribute(regResultMessageAttribute, regResultMessage);
                return "redirect:/base_page";
            } else {
                newUserInfo.setPassword(password);
                model.addAttribute(userInfoAttribute, newUserInfo);
                model.addAttribute(messageLoginExistAttribute, newUserInfo.getLogin());
                model.addAttribute(registrationAttribute, regStatus);
                return "baseLayout";
            }
        } catch (ServiceException e) {
            return "error";
        }
    }
}
