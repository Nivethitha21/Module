import java.util.ArrayList;
import java.util.List;

public class Booking {
private List<Passenger> pas=new ArrayList<Passenger>();
public void printPas()
{
	System.out.println("Seat no   Passenger id Passenger Name      Age   Gender   Berth");
	for(int i=0;i<pas.size();i++)
	{
		Passenger p=pas.get(i);
		System.out.println("\n"+p.getSeatNo()+"\t\t"+p.getPid()+"\t\t"+p.getName()+"\t"+p.getAge()+"\t\t"+p.getGender()+"\t"+p.berthPreference);
	}
}
public void addPas(Passenger p)
{
	pas.add(p);
	
}
}
