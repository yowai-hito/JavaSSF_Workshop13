package sg.edu.nus.workshop13.util;

import org.springframework.ui.Model;
import sg.edu.nus.workshop13.model.Contact;

import java.util.List;
import java.util.Set;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.ApplicationArguments;

public class Contacts {
        private static final Logger logger = LoggerFactory.getLogger(Contacts.class);
        
        public void saveContact(Contact contact, Model model, ApplicationArguments applicationArguments){
                String dataFilename = contact.getId();
                Set<String> optnames = applicationArguments.getOptionNames();
                String[] optnamesArr = optnames.toArray(new String[optnames.size()]);
                List<String> optValues = applicationArguments.getOptionValues(optnamesArr[0]);
                String[] optValuesArr = optValues.toArray(new String[optValues.size()]);
                PrintWriter printWriter = null;
                try{
                        FileWriter fileWriter = 
                                new FileWriter(optValuesArr[0] +"/"+dataFilename, Charset.forName("utf-8"));
                        printWriter = new PrintWriter(fileWriter);
                        printWriter.println(contact.getName());
                        printWriter.println(contact.getEmail());
                        printWriter.println(contact.getPhoneNumber());
                }catch(IOException e){
                        logger.error(e.getMessage());
                }finally {
                        printWriter.close();
                }
                
                model.addAttribute("contact", new Contact(contact.getName(), 
                        contact.getEmail(), 
                        contact.getPhoneNumber()));
        }

        public void getContactById(Model model, String contactId, ApplicationArguments applicationArguments){
                Set<String> optnames = applicationArguments.getOptionNames();
                String[] optnamesArr = optnames.toArray(new String[optnames.size()]);
                List<String> optValues = applicationArguments.getOptionValues(optnamesArr[0]);
                String[] optValuesArr = optValues.toArray(new String[optValues.size()]);
                Contact cResp = new Contact();
                try {
                        Path filePath = new File(optValuesArr[0]+ '/' + contactId).toPath();
                        Charset charset = Charset.forName("utf-8");        
                        List<String> stringList = Files.readAllLines(filePath, charset);
                        String[] stringArray = stringList.toArray(new String[]{});
                        cResp.setName(stringArray[0]);
                        cResp.setEmail(stringArray[1]);
                        cResp.setPhoneNumber(Integer.parseInt(stringArray[2]));
                }catch(IOException e){
                        logger.error(e.getMessage());
                }
                model.addAttribute("contact", cResp);
        }
}
