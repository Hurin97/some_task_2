import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Person")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonLite {
    @XmlAttribute
    public String name;

    public PersonLite() {
    }

    public PersonLite(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
