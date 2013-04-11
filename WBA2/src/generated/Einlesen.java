package generated;
import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;


public class Einlesen {
	public static void main(String[] args) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Rezept.class);
		
		// Java objekte aus XML erstellen
		Unmarshaller um=context.createUnmarshaller();
		Rezept r=(Rezept) um.unmarshal(new FileInputStream("src/Aufgabe3Rezept.xml"));
		
		System.out.println(r.getZutaten().getZutat().get(1).getMenge());
		System.out.println(r.getZubereitung().getArbeitszeit());
		System.out.println(r.getPortionen().getAnzahl());
		
		
	}

}
