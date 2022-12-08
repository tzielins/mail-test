/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.biordm.mail.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author tzielins
 */

@SpringBootApplication
public class MailingApplication {
    final Logger log = LoggerFactory.getLogger(this.getClass());
    
    
    public static void main(String[] args) {
        SpringApplication.run(MailingApplication.class, args);
    }        
    
    
     

}



