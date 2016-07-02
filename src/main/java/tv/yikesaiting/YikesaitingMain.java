package tv.yikesaiting;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
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
    public String index(Model model) {
        Person single = new Person("zhang", 23);
        List<Person> people = new LinkedList<Person>();
        people.add(new Person("li", 22));
        people.add(new Person("wang", 12));
        people.add(new Person("keng", 234));
        model.addAttribute("singlePerson", single);
        model.addAttribute("people", people);
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(YikesaitingMain.class, args);
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainerFactory() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection securityCollection = new SecurityCollection();
                securityCollection.addPattern("/*");
                securityConstraint.addCollection(securityCollection);
                context.addConstraint(securityConstraint);
            }
        };
             tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }

    @Bean
    public Connector httpConnector(){
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8888);
        return connector;
    }
}
