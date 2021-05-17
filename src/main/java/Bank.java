import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "Bank")
@XmlAccessorType(XmlAccessType.FIELD)

public class Bank {
    @XmlElement(name = "Person")
    public List<Person> personList=new ArrayList<>();
    @XmlAttribute(name = "wallet")
    public BigDecimal wallet;

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public BigDecimal getWallet() {
        return wallet;
    }

    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }
}
