import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.time.DateTimeException;
@SuppressWarnings("unused")
public class Age {
	int Days,Months,Years;
	LocalDate Present = LocalDate.now();
	LocalDate birthday = LocalDate.of(Years,Months,Days);
	Period Difference = Period.between(birthday, Present);
	public Age() {
		System.out.print(Difference.getYears()+" Years, ");
		System.out.print(Difference.getMonths()+" Months ");
		System.out.print("and "+Difference.getDays()+" Days.");
		System.out.println(" ");
		System.out.println("You have been alive for "+Difference.toTotalMonths()+" Months");
		System.out.println("You were born in "+birthday.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));

	}
}

class ReadAge extends Age {
	public ReadAge() {
		try(Scanner getNumbers = new Scanner(System.in)) {
			System.out.println("Enter Year of birth");
			Years= getNumbers.nextInt();
			System.out.println("Enter Month");
			Months= getNumbers.nextInt();
			System.out.println("Enter Day");
			Days= getNumbers.nextInt();
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
			} catch(InvalidInputDate error) { //OR sign to handle multiple exceptions(InvalidInputDate | DateTimeException e)
				System.out.println("Invalid Date. Try Again.");
			} catch(WayOld e) {
				System.out.println("You're either a time traveler or found the elixir of life");
			} catch (InputMismatchException e) {
				System.out.println("Nice Try.");
			} catch (DateTimeException e) {
				System.out.println("Invalid Date. Try Again.");
				System.out.println(birthday.getYear()+" is not a leap year.");
			}
	}
} 




@SuppressWarnings("serial")
class InvalidInputDate extends Exception {
}
@SuppressWarnings("serial")
class WayOld extends Exception {
}
