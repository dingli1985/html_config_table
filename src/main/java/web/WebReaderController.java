package web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value ="/reader" )
public class WebReaderController {

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String toReader(){
        return "reader";
    }
}
