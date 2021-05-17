import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "total")
@XmlAccessorType(XmlAccessType.FIELD)
public class Total {
    @XmlElementWrapper(name ="result")
    @XmlElement(name = "Person")
    public List<Person> result;
    @XmlElementWrapper(name ="minimum")
    @XmlElement(name = "Person")
    public List<PersonLite> minimum;

    public Total() {
    }

    public Total(List<Person> result, List<PersonLite> minimum) {
        this.result = result;
        this.minimum = minimum;
    }

    public List<Person> getResult() {
        return result;
    }

    public void setResult(List<Person> result) {
        this.result = result;
    }

    public List<PersonLite> getMinimum() {
        return minimum;
    }

    public void setMinimum(List<PersonLite> minimum) {
        this.minimum = minimum;
    }
}
