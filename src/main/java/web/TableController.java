package web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/tables") 
public class TableController {


	@RequestMapping(value = "/query/{tableId}", method = RequestMethod.GET)
	public String getTable(@PathVariable(value = "tableId") String id) {
		System.out.println("id========="+id);
		return "Query Table Id=====["+id+"]";
	}
}
