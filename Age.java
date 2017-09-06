import java.time.LocalDate;
import java.time.Period;
import java.time.format.TextStyle;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.time.DateTimeException;

public class Age {
	int Days,Months,Years;
	LocalDate Present = LocalDate.now();
	LocalDate birthday;
	Period Difference;

	public Age() {
		try {
			ReadAge();
			CalculateAge();
			Display();	
		} catch (DateTimeException e) {
			System.out.println("Invalid Date. Try Again.");
			System.out.println(Years+" is not a leap year.");
			System.out.println("caught in constructor");
		} catch (NullPointerException e) { // I don't think it's necessary since I stop everything at DateTimeException.
			System.out.println("Null caught in constructor");//anyway it's caused by empty birthday and difference in display
		} catch (InputMismatchException e) {
			System.out.println("Invalid input.");
		} catch(InvalidInputDate error) {
			System.out.println("Invalid Date. Try Again.");
		} catch(WayOld e) {
			System.out.println("You're either a time traveler or found the elixir of life");
		}
	}


	public void CalculateAge() throws DateTimeException {
		birthday = LocalDate.of(Years,Months,Days);
		Difference = Period.between(birthday, Present);
	}
	public void Display() throws NullPointerException {
		System.out.print(Difference.getYears()+" Years, "); // looks like when I don't enter a valid year, this become empty
		System.out.print(Difference.getMonths()+" Months ");// and return a null pointer
		System.out.println("and "+Difference.getDays()+" Days.");
		System.out.println("You have been alive for "+Difference.toTotalMonths()+" Months");
		System.out.println("You were born in "+birthday.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));
	}

	public void ReadAge() throws InputMismatchException, InvalidInputDate, WayOld {
		Scanner getNumbers = new Scanner(System.in);
		System.out.println("Enter Year of birth");
		Years= getNumbers.nextInt();
		System.out.println("Enter Month");
		Months= getNumbers.nextInt();
		System.out.println("Enter Day");
		Days= getNumbers.nextInt();
		getNumbers.close();
		if (Years <= 0 || Months <=0 || Days <= 0 || Months > 12 || Days > 31) {
			throw new InvalidInputDate();
		}
		if (Years > Present.getYear() || Years < (Present.getYear()-100)) {
			throw new WayOld();
		}
		if (Years == Present.getYear()) {
			if (Months > Present.getMonthValue()) {
				throw new WayOld();
			}
			else if(Days > Present.getDayOfMonth()) {
				throw new WayOld();
			}
			else if(Days == Present.getDayOfMonth()){
				throw new InvalidInputDate();
			}
		}
		/* CalculateAge(); this calls the method to give birthday and difference a value,but when values aren't assigned
			the call in main to the display method gives a NullPointerException.
			I'll see what I can do to improve.*/
		//Display(); I put display here to eliminate Null pointer
	}  //OR sign to handle multiple exceptions(InvalidInputDate | DateTimeException e)
}

/* Even though after re-writing my code to use a more Object-oriented style, I was hesitant and thought when I first run it
 * that it will not work, but it did, I still think that it needs better writing, I'm either not convinced or
 * still not used to this style. 
 * EDIT:-  I always change my code numerous times and now I think I finally made it to work without problems
 * the only thing is that I feel it's not so different from my previous work, it's all the same except I 
 * divided some lines on methods without putting everything in the constructor.*/



@SuppressWarnings("serial")
class InvalidInputDate extends Exception {
}
@SuppressWarnings("serial")
class WayOld extends Exception {
}
