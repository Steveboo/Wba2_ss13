package generated;
import generated.Rezept.Kommentare.Kommentar;
import generated.Rezept.Zutaten.Zutat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


public class Einlesen {
	public static void main(String[] args) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Rezept.class);
		
		// Java objekte aus XML erstellen
		Unmarshaller um=context.createUnmarshaller();
		Rezept r=(Rezept) um.unmarshal(new FileInputStream("src/Aufgabe3Rezept.xml"));
		int alt=0;
		Scanner in= new Scanner(System.in);
		
		System.out.println("Zum ausgeben 1 dr�cken, neuer Kommentar mit 2");
		int a=in.nextInt();
		String nameAutor="";
		String eingegebenerKommentar="";
		if (a==1)
				{
			for (Zutat z:r.getZutaten().getZutat()){
				
				System.out.println(z.getMenge()+" "+z.getEinheit()+" "+z.getName());
			}
			
			System.out.println("f�r "+r.getPortionen().getAnzahl()+" Portionen");
			System.out.println("Arbeitszeit"+r.getZubereitung().getArbeitszeit());
			System.out.println("Schwierigkeitsgrad "+r.getZubereitung().getSchwierigkeitsgrad());
			System.out.println("Brennwert: "+r.getZubereitung().getBrennwert());
			System.out.println(r.getZubereitung().getBeschreibung());

			for(Kommentar k:r.getKommentare().getKommentar()){
				System.out.println(k.getAutor());
				System.out.println(k.getId());
				System.out.println(k.getDatum());
				System.out.println(k.getZeit());
				System.out.println(k.getValue());
					}
			}
		else if (a==2){
			System.out.println("geben Sie ihren Namen ein:");
			nameAutor=in.next();
			System.out.println("geben Sie ihren den Kommentar ein:");
			eingegebenerKommentar=in.next();
		}
		else {
			System.out.println("Fehler!");
			return;
		}
		Date now = new Date();
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(now);
		XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		
		for(Kommentar k:r.getKommentare().getKommentar() )
		{  
		String stringZahl = k.getId();
	    int intZahl = Integer.parseInt(stringZahl);
		if(intZahl>alt)
			alt=intZahl;
	  }
		Kommentar k = new Kommentar();
		//Kommentar erstellen
		k.setAutor(nameAutor);
		k.setDatum(date2);
		String altString = String.valueOf(alt+1);
		k.setId(altString);
		//k.setId((new Integer((int)(Math.random()*42*42))).toString()); // Random ID
		k.setZeit(date2); 
		k.setValue(eingegebenerKommentar);
		
		r.getKommentare().getKommentar().add(k);
		
		
		Marshaller ma=context.createMarshaller();
		ma.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		ma.marshal(r, new FileOutputStream("src/Aufgabe3Rezept.xml"));
		
	}
	

}
