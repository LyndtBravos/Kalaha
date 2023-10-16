package com.greenhouse.kalaha;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EndPoint
{

	Mancala game = new Mancala();

	@GetMapping("/hello-world/{name}")
	public String sayHello(@PathVariable String name) {
		System.out.println("This is Spring Boot, " + name + "!");
		return "Hello: " + name;
	}

	@GetMapping("/mancala/play/{input}")
	public String play(@PathVariable int input){
		return game.play(input);
	}

	@GetMapping("/mancala/showStats")
	public String toString() {
		return game.toString();
	}

}