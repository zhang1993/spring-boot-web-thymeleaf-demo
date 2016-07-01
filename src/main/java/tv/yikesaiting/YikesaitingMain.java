package tv.yikesaiting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by littlekeng on 16/7/1.
 */
@Controller
@SpringBootApplication
public class YikesaitingMain {
    @RequestMapping("/")
    public String index(Model model){
        Person single = new Person("zhang",23);
        List<Person> people = new LinkedList<Person>();
        people.add(new Person("li",22));
        people.add(new Person("wang",12));
        people.add(new Person("keng",234));
        model.addAttribute("singlePerson",single);
        model.addAttribute("people",people);
        return "index";
    }

    public static void main(String[] args){
        SpringApplication.run(YikesaitingMain.class,args);
    }
}
