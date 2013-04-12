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
		
		Scanner in= new Scanner(System.in);
		
		System.out.println("Zum ausgeben 1 drücken, neuer Kommentar mit 2");
		int a=in.nextInt();
		String nameAutor="";
		String eingegebenerKommentar="";
		if (a==1)
				{
			for (Zutat z:r.getZutaten().getZutat()){
				
				System.out.println(z.getMenge()+" "+z.getEinheit()+" "+z.getName());
			}
			
			System.out.println("für "+r.getPortionen().getAnzahl()+" Portionen");
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
		
		Kommentar k = new Kommentar();
		
		k.setAutor(nameAutor);
		k.setDatum(r.getKommentare().getKommentar().get(0).getDatum());
		k.setId((new Integer((int)(Math.random()*42*42))).toString());
		k.setZeit(r.getKommentare().getKommentar().get(0).getZeit());
		k.setValue(eingegebenerKommentar);
		
		r.getKommentare().getKommentar().add(k);
		
		
		Marshaller ma=context.createMarshaller();
		ma.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		ma.marshal(r, new FileOutputStream("src/Aufgabe3Rezept.xml"));
		
	}

}
