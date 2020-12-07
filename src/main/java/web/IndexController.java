package web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.TABLE;
import entity.TD;
import entity.TR;

@Controller
public class IndexController {

	@RequestMapping("/index")
	public String index(Model model) {
		TABLE table= new TABLE();
		List<TR> trList=new ArrayList<TR>();
		List<TD> tdList1=new ArrayList<TD>();
		tdList1.add(new TD(20,20,1,1,1,"第一行第一列"));
		tdList1.add(new TD(20,20,1,1,1,"第一行第二列"));
		tdList1.add(new TD(20,20,1,1,1,"第一行第三列"));
		tdList1.add(new TD(20,20,1,1,1,"第一行第四列"));
		tdList1.add(new TD(20,20,1,1,1,"第一行第五列"));
		tdList1.add(new TD(20,20,1,1,1,"第一行第六列"));
		
		System.out.println("Table==========================>1111111");

		List<TD> tdList2=new ArrayList<TD>();
		tdList2.add(new TD(200,20,1,1,1,"第二行第一列"));
		tdList2.add(new TD(200,20,1,1,1,"第二行第二列"));
		tdList2.add(new TD(200,20,1,1,1,"第二行第三列"));
		tdList2.add(new TD(200,20,1,1,1,"第二行第四列"));
		tdList2.add(new TD(200,20,1,1,1,"第二行第五列"));
		tdList2.add(new TD(200,20,1,1,1,"第二行第六列"));
		List<TD> tdList3=new ArrayList<TD>();
		tdList3.add(new TD(200,20,1,1,1,"第三行第一列"));
		tdList3.add(new TD(200,20,1,1,1,"第三行第二列"));
		tdList3.add(new TD(200,20,1,1,1,"第三行第三列"));
		tdList3.add(new TD(200,20,1,1,1,"第三行第四列"));
		tdList3.add(new TD(200,20,1,1,1,"第三行第五列"));
		tdList3.add(new TD(200,20,1,1,1,"第三行第六列"));
		List<TD> tdList4=new ArrayList<TD>();
		tdList4.add(new TD(200,20,1,1,1,"第四行第一列"));
		tdList4.add(new TD(200,20,1,1,1,"第四行第二列"));
		tdList4.add(new TD(200,20,1,1,1,"第四行第三列"));
		tdList4.add(new TD(200,20,1,1,1,"第四行第四列"));
		tdList4.add(new TD(200,20,1,2,1,"第四行第五列"));
		System.out.println("Table==========================>222222");

		trList.add(new TR("1-2-1","TH",tdList1));
		trList.add(new TR("1-2-2","TH",tdList2));
		trList.add(new TR("1-2-3","TH",tdList3));
		trList.add(new TR("1-2-4","TH",tdList4));
		table.setList(trList);
		

		model.addAttribute("table",table);

		return "index";
	}
}