package generated;
import generated.Rezept.Kommentare.Kommentar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


public class Einlesen {
	public static void main(String[] args) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Rezept.class);
		
		// Java objekte aus XML erstellen
		Unmarshaller um=context.createUnmarshaller();
		Rezept r=(Rezept) um.unmarshal(new FileInputStream("src/Aufgabe3Rezept.xml"));
		
		System.out.println(r.getZutaten().getZutat().get(1).getMenge());
		System.out.println(r.getZubereitung().getArbeitszeit());
		System.out.println(r.getPortionen().getAnzahl());
		System.out.println(r.getKommentare().getKommentar().get(0).getValue());
		Kommentar k = new Kommentar();
		k.setAutor("max");
		k.setDatum(r.getKommentare().getKommentar().get(0).getDatum());
		k.setId("2");
		k.setZeit(r.getKommentare().getKommentar().get(0).getZeit());
		k.setValue("neuer kommentar");
		
		r.getKommentare().getKommentar().add(k);
		System.out.println(r.getKommentare().getKommentar().get(1).getValue());
		
		Marshaller ma=context.createMarshaller();
		ma.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		ma.marshal(r, new FileOutputStream("src/Aufgabe3Rezept.xml"));
		
	}

}
