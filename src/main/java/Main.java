import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws JAXBException {
        JAXBContext reader_context = JAXBContext.newInstance(Bank.class);
        Unmarshaller unmarshaller = reader_context.createUnmarshaller();
        File reader=new File("src/main/resources/account1");
        Bank bank =(Bank) unmarshaller.unmarshal(reader);


        BigDecimal maxWallet=bank.getPersonList().stream()
                .max((o1, o2) -> o1.getWallet().compareTo(o2.getWallet())).get().getWallet();
        List<Person> people = bank.getPersonList().stream().peek((p)-> {
            p.setAppendFromBank(maxWallet.subtract(p.getWallet()));
        }).collect(Collectors.toList());
        BigDecimal average = (bank.getWallet().subtract(people.stream().map(Person::getAppendFromBank)
                .reduce(BigDecimal.ZERO,BigDecimal::add)))
                .divide(BigDecimal.valueOf(people.size()),2, RoundingMode.DOWN);
        people.stream().peek((p)-> {
            p.setAppendFromBank(average.add(p.getWallet()));
        }).collect(Collectors.toList());
        BigDecimal minimalAppend=people.stream().min((p1,p2)->p1.getAppendFromBank().compareTo(p2.getAppendFromBank())).get().getAppendFromBank();
        List<PersonLite> pL= people.stream().filter((p1)->p1.getAppendFromBank().compareTo(minimalAppend)==0).collect(Collectors.toList()).stream()
                .map((p) ->{
                    PersonLite pl= new PersonLite(p.getName());
                    return pl;
                }).collect(Collectors.toList());
        Total total=new Total(people,pL);

        File writer = new File("src/main/resources/result_account.xml");
        JAXBContext writer_context=JAXBContext.newInstance(Total.class);
        Marshaller marshaller = writer_context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(total,writer);
    }
}
