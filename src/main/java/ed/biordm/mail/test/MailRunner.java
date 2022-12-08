/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.biordm.mail.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author tzielins
 */
@Component
public class MailRunner implements ApplicationRunner{

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    final Mailer mailer;


    @Autowired
    public MailRunner(Mailer mailer) {
        this.mailer = mailer;
    }

    @Override
    public void run(ApplicationArguments args) {

        logger.info("I was called");
        
        try {
            mailer.send("biodare@ed.ac.uk", "Test sending", "Message body");
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

}
