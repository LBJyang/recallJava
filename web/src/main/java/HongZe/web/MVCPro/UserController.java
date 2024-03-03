package HongZe.web.MVCPro;

public class UserController {
	@GetMapping("/signin")
	public ModelAndView signin() {
		return null;
	}

	@GetMapping("/signout")
	public ModelAndView signout() {
		return null;
	}

	@PostMapping("/signin")
	public ModelAndView signin(SigninBean bean) {
		return null;

	}
}
