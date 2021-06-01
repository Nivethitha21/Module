import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketAlloc {
	Scanner sc=new Scanner(System.in);
	static Integer wait=0;
	static int m=0,u=0,l=0,j=0,sp=1,rear=0,front=0,bookingCount=0;
	static Passenger[] Berth=new Passenger[65];
	static Passenger[] RaC=new Passenger[17];
	List<Booking> booking=new ArrayList<Booking>();
	ArrayList<Passenger> waitList=new ArrayList<Passenger>();
	public void bookTicket()
	{
		System.out.println("Enter number of Tickets");
		int c=sc.nextInt();
		boolean flag=false;
		System.out.println("Enter Details: ");
		ArrayList<Passenger> temp=new ArrayList<Passenger>();
		Booking book=new Booking();
		//Check if available waitlist seat is less than entered no of tickets(Worst-Case Check)
		while(c > 0)
		{
			if(wait > 10) {
				System.out.println("No tickets available! ");
				break;}
			Passenger p = new Passenger();
			
			System.out.println("Enter Name: ");
			p.setName(sc.next());
			System.out.println("Enter age: ");
			p.setAge(sc.nextInt());
			System.out.println("Enter gender: ");
			p.setGender(sc.next());
			if(p.getAge()>5) {
				System.out.println("\nEnter berth preference: \n 1.Lower \n 2.Middle \n 3.Upper \n 4.Side Upper \n");
			p.setBerthPreference(sc.nextInt());}
			temp.add(p);
			//Check if a child is travelling in given Booking
			if(p.getAge() <= 5)
				flag=true;
			c--;
		}
		//Seat Allocation Code
		while(temp.size()>0)
		{
			Passenger p=temp.remove(0);
			//Seat not allocated for children below 5
			if(p.getAge()<=5)
				continue;
			int b=p.getBerthPreference();
			//Check if Booking is to be done for RaC
			if(l>7 && m>7 && u>7 && sp>7)
			{
				if(front>15) {
					if(rear==0)
					{
						waitList.add(p);
						System.out.println("Waitlist #0"+wait);
						wait++;
						}
					else {
						front=0;
						RaC[front]=p;
						int t=(front%2!=0 && front!=0)?front-1:front;
						p.setSeatNo(t*8+7);
						front++;
					}}
					else
					{
						if(RaC[front]==null) {
						RaC[front]=p;
						p.setSeatNo((front/2)*8+7);
						front++;
					}
					else {
						waitList.add(p);
						System.out.println("Waitlist #0"+wait);
						wait++;
						}
						}
				System.out.println("Seat no #RAC"+p.getSeatNo()+" Passenger Name "+p.getName());
				}
			else {
			if(b==4)
			{
				if(sp>8)
				{
					//berthpreference set to 1 if berth asked is not available for women travelling with Children or passenger age>60
					if(p.getAge()>60 || (p.getGender().equalsIgnoreCase("female")&&flag==true)) { b=1;System.out.print("Entered");}
					else b=0;
				}else {
				Berth[8*sp]=p;
				p.setSeatNo(8*sp);
				sp++;}
			}
			if(b==2)
			{
				if(m>7)
				{
					if(p.getAge()>60 || (p.getGender().equalsIgnoreCase("female")&&flag==true)) b=1;
					else
						b=0;
				}
				else {
				if(Berth[8*m+2]==null) {
					Berth[8*m+2]=p;
					p.setSeatNo(8*m+2);
				}
				else {
					Berth[8*m+5]=p;
					p.setSeatNo(8*m+5);
					m++;
				}
			}}
			if(b==3)
			{
				if(u>7) {
					if(p.getAge()>60 || (p.getGender().equalsIgnoreCase("female")&&flag==true))
						b=1;
					else
					b=0;
				}else {
				if(Berth[8*u+3]==null) {
					Berth[8*u+3]=p;p.setSeatNo(8*u+3);}
				else {
					Berth[8*u+6]=p;
					p.setSeatNo(8*u+6);
					u++;
				}}
			}
			if(b==1)
			{
				if(l>7)
				{
					b=0;
				}
				else {
				if(Berth[8*l+1]==null) {
					Berth[8*l+1]=p;
					p.setSeatNo(8*l+1);}
				else {
					Berth[8*l+3]=p;
					p.setSeatNo(8*l+3);
					l++;
				}
				}}
				//random berth booking if entered berth not available
			if(b==0)
			{
				if(l<=7)
				{
					if(Berth[8*l+1]==null) {
						Berth[8*l+1]=p;
						p.setSeatNo(8*l+1);	
					}
					else {
						Berth[8*l+3]=p;
					p.setSeatNo(8*l+4);
					l++;}
				}
				if(m<=7)
				{
					if(Berth[8*m+2]==null) {
						Berth[8*m+2]=p;
						p.setSeatNo(8*m+2);
					}else {
						Berth[8*m+5]=p;
					p.setSeatNo(8*m+5);
					m++;}
				}
				else if(u<=7)
				{
					if(Berth[8*u+3]==null) {
						Berth[8*u+3]=p;
						p.setSeatNo(8*u+3);
					}else {
						Berth[8*u+6]=p;
						p.setSeatNo(8*u+6);
						u++;
					}}
				else if(sp<=8)
				{
					Berth[8*sp]=p;
					p.setSeatNo(8*sp);
					sp++;
				}
				
			}
		}
			book.addPas(p);
			}
	
		booking.add(book);
		System.out.println("Your booking ID: "+bookingCount);
		bookingCount++;
	}
	public void cancelTicket()
	{
		System.out.println("Enter seatNo to cancel booking: (Enter -1 to exit) ");
		int c=sc.nextInt();
		while(c!=-1 && c<65)
		{
			//check if cancel seat is an RaC seat
			if(c%8==7)
			{
				System.out.println("Enter Passenger ID");
				int pi=sc.nextInt();
				if(RaC[c/8].getPid()==pi) {
					System.out.println("Seat No "+c+" Cancelled by pid "+RaC[c/8].getPid());
					
					if(waitList.size()==0)
						RaC[c/8]=null;
					else {
						Passenger temp2=waitList.remove(0);
					RaC[c/8]=temp2;
					wait--;}
				}
				else if(RaC[(c/8)+1].getPid()==pi) {
					System.out.println("Seat No "+c+" Cancelled by pid "+RaC[c/8].getPid());
					//Moving passenger from waitlist to RaC
					if(waitList.size()==0)
						RaC[(c/8)+1]=null;
					else {
					Passenger temp2=waitList.remove(0);
					RaC[(c/8)+1]=temp2;
					wait--;}
			}}
			if(rear>15 && RaC[front]==null || RaC[rear]==null)
			{
				System.out.println("Seat No "+c+" Cancelled by pid "+Berth[c].getPid());
				Berth[c]=null;
			}
			else if(rear>15)
			{
				if(RaC[front]!=null)
				{
					rear=front;
				}
			}
			//moving passenger from rac to cancelled
			else {
			System.out.println("Seat No "+c+" Cancelled by pid "+Berth[c].getPid());
			Passenger temp=RaC[rear];
			Berth[c]=temp;
			System.out.println("#RAC"+(8*(rear/2)+7)+" moved to "+c);
			rear++;
				//moving from waitlist to rac currently promoted
			if(waitList.size()==0)
			{
				RaC[rear-1]=null;
			}else {
			Passenger temp2=waitList.remove(0);
			System.out.println("Waitlist 0"+temp2.getPid()+" moved to #RAC"+(8*rear+7));
			RaC[rear-1]=temp2;
			wait--;
			}}
			System.out.println("Enter seatNo to cancel booking: (Enter -1 to exit) ");
			c=sc.nextInt();
			
		}
	}
	public void printBookedTickets()
	{
		System.out.println("Enter booking ID:");
		int bookID=sc.nextInt();
		if(bookID>booking.size())
			System.out.println("No Booking found");
		else {
		Booking b=booking.get(bookID);
		b.printPas();
		}}
	public void printavailableTickets()
	{
		int count=0,countR=0;
		for(int i=0;i<16;i++)
		{
			for(int j=1;j<=3;j++)
			{
					if(Berth[(i*3)+j]==null) {count++;
						System.out.print("F   ");}
					else
						System.out.print(Berth[(i*3)+j].getPid()+"   ");
			}
			
			if(i%2==1) {
				if(Berth[(i*4)+4]==null) {count++;
				System.out.print("F   ");}
			else
				System.out.print(Berth[(i*4)+4].getPid()+"   ");
				System.out.println();
			}
			System.out.println();
		}
		System.out.println("--------------------RAC Tickets -----------------");
		for(int i=0;i<16;i++)
		{
			if(RaC[i]==null) {countR++;
				System.out.print(" F");}
			else
				System.out.print(" "+RaC[i]);}
		
		System.out.println("\nTotal Available Tickets: "+count);
		System.out.println("\nTotal Available RaC Tickets: "+countR);
	}
	
	public static void main(String[] args)
	{
		TicketAlloc a=new TicketAlloc();
		System.out.println("\n1. Book \n2. Cancel \n3. Print booked Tickets \n4. Print available Tickets \n 5. Exit");
		System.out.println("Enter choice");
		Scanner sc=new Scanner(System.in);
		int ch=sc.nextInt();
		while(ch!=5)
		{
		switch(ch)
		{
		case 1:a.bookTicket();
				break;
		case 2:a.cancelTicket();
				break;
		case 3:a.printBookedTickets();
				break;
		case 4: a.printavailableTickets();
				break;
		case 5: break;
		default: System.out.println("Invalid Operation");
				break;
		}
		System.out.println("\n1. Book \n2. Cancel \n3. Print booked Tickets \n4. Print available Tickets \n 5. Exit");
		System.out.println("Enter choice");
		ch=sc.nextInt();
		
	}
}}
