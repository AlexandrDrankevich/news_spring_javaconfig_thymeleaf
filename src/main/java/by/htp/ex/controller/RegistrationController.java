package by.htp.ex.controller;

import by.htp.ex.entity.UserInfo;
import by.htp.ex.entity.UserRole;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private UserService service;
	private static final String userInfoAttribute = "newUserInfo";
	private static final String regResultMessageAttribute = "regMessage";
	private static final String messageLoginExistAttribute = "messageLoginExist";
	private static final String registrationAttribute = "reg";
	private static final String regStatus = "active";
	private static final String regResultMessage = "Successful registration!";
	private static final String defaultRole = "ROLE_USER";

	@RequestMapping("/showForm")
	public String showForm(@ModelAttribute(userInfoAttribute) UserInfo newUserInfo, Model model) {
		model.addAttribute(registrationAttribute, regStatus);
		return "baseLayout";
	}

	@RequestMapping("/do_registration")
	public String doRegistration(@ModelAttribute(userInfoAttribute) UserInfo newUserInfo, RedirectAttributes attr,
			Model model) {
		String password = newUserInfo.getPassword();
		String springPassword = "{noop}" + password;
		newUserInfo.setPassword(springPassword);
		try {
			UserRole defaultUserRole = new UserRole();
			defaultUserRole.setLogin(newUserInfo.getLogin());
			defaultUserRole.setRole(defaultRole);
			List<UserRole> userRoleList = new ArrayList<UserRole>();
			userRoleList.add(defaultUserRole);
			newUserInfo.setUserRole(userRoleList);
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
