package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Cart;
import com.example.demo.domain.OrderDetail;
import com.example.demo.domain.User;
import com.example.demo.service.CartService;
import com.example.demo.service.GenreService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import com.example.demo.validator.UserValidator;

@Controller
public class AccountController {
	CartService cartService;
	
	@Autowired
	public void CartService(CartService s){
		cartService = s;
	}
	
	@Autowired
	GenreService genreService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	UserValidator userValidator;
	
	@Autowired
	UserService userService;
	
    public UserValidator getCustomerValidator() {
        return userValidator;
    }
 
    public void setCustomerValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }
	
	
	///////////////LOGON///////////////////////////////////////////////////
    @RequestMapping("/Logon")
	public String logOn(Model model){
		int quantity = cartService.getQuantity();
		model.addAttribute("quantity",quantity);
		return"account/logon";
	}
	
    @RequestMapping(value = "/onLogon", method = RequestMethod.POST)
	public String onLogon(@RequestParam("username") String username
			, @RequestParam("password") String password, Model model){
    	List<User> users = userService.getAll();
    	int userId = -1;
    	boolean haveAccount =false;
    	for(User u : users){
    		if(username.equals(u.getUsername()) && password.equals(u.getPassword())){
    			userId = u.getUserId();
    			haveAccount = true;
    			break;
    		}
    	}
    	
    	if(haveAccount){
    		return "redirect:/Order/" + Integer.toString(userId);
    	}
    	return "account/fail";
    	
	}
	
	//////////REGISTER//////////////////////////
	@RequestMapping(value ="/Register", method = RequestMethod.GET)
	public String register(Model model){
		int quantity = cartService.getQuantity();
		model.addAttribute("quantity",quantity);
		model.addAttribute("User",new User());
		return "account/register";
	}
	
	@RequestMapping(value ="/Register", method = RequestMethod.POST)
    public String checkRegister(@Valid @ModelAttribute("User") User User, BindingResult bindingResult, Model model) {

		userValidator.validate(User, bindingResult);
		
		if (bindingResult.hasErrors()) {
            return "account/register";
        }
        else{
        	User.setRole(0);
        	userService.insertUser(User);
        	int lastOneId = userService.takeNewestUser();
        	return "redirect:/Order/" + Integer.toString(lastOneId);
        }
    }
	////////////////////////////ORDER////////////////////////////////////
	@RequestMapping("/Order/{userId}")
	public String order(@PathVariable String userId,Model model){
		int quantity = cartService.getQuantity();
		model.addAttribute("quantity",quantity);
		OrderDetail orderDetail = new OrderDetail();
		model.addAttribute("userId",userId);
		model.addAttribute("OrderDetail", orderDetail);
		return "checkout/orderDetails";
	}
	
	@RequestMapping(value = "/Order/{userId}", method = RequestMethod.POST)
	public String checkOrder(@PathVariable String userId,@Valid @ModelAttribute("OrderDetail") OrderDetail OrderDetail, BindingResult bindingResult,Model model){

		if (bindingResult.hasErrors()) {
			return "checkout/orderDetails";
        }
		else{
			double total = 0;
			List<Cart> carts = cartService.getAll();
			for(Cart c : carts){
				double price = c.getPrice() * c.getCount();
				total += price;
			}
		
			OrderDetail.setUserId(Integer.parseInt(userId));
			OrderDetail.setUsername(userService.getNameByUserId(userId));
			OrderDetail.setTotal(total);
			
			orderService.insertOrder(OrderDetail);
			int getLasterOrderId = orderService.getLastedOrderId();
			model.addAttribute("orderId",getLasterOrderId);
			return "redirect:/Success/" + Integer.toString(getLasterOrderId);
		}
	}
	
	//////////////////////////SUCCESS///////////////////////////////////////
	@RequestMapping("/Success/{orderId}")
	public String success(@PathVariable String orderId, Model model){
		cartService.deleteAll();
		
		int quantity = cartService.getQuantity();
		model.addAttribute("quantity",quantity);
		model.addAttribute("orderId", orderId);
		return "checkout/success";
	}
}
