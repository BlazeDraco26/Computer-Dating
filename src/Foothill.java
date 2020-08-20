// Lab #8
// Jordan Cheng

public class Foothill
{ 
   public static void main(String[] args) {
      
      // instantiate DateProfile objects
      DateProfile[] dateProfile = new DateProfile[4];
      dateProfile[0] = new DateProfile('F', 'M', 9, 3, 8, "Test Run");
      dateProfile[1] = new DateProfile('M', 'F', 5, 5, 5, "Lab Coat");
      dateProfile[2] = new DateProfile('F', 'F', 10, 9, 2, "Mix Match");
      dateProfile[3] = new DateProfile('M', 'M', 8, 1, 10, "Spill Water");
      
      // display all combinations
      for (int i = 0; i < dateProfile.length; i++) {
         System.out.println();
         for (int j = 0; j < dateProfile.length; j++) {         
            DateProfile.displayTwoProfiles(dateProfile[i], dateProfile[j]);
         }
      }
   }
}

class DateProfile
{
   // define constants
   public static final int MIN_ROMANCE = 1;
   public static final int MAX_ROMANCE = 10;
   public static final int MIN_FINANCE = 1;
   public static final int MAX_FINANCE = 10;
   public static final int MIN_DISTANCE = 1;
   public static final int MAX_DISTANCE = 10;
   public static final int MIN_NAME_LEN = 1;
   public static final int MAX_NAME_LEN = 30;
   public static final int MIN_VALUE = 1;
   public static final int MAX_VALUE = 10;
   
   public static final char DEFAULT_GEND = 'M';
   public static final char DEFAULT_SEARCH_GEND = 'F';
   public static final int DEFAULT_ROMANCE = 5;
   public static final int DEFAULT_FINANCE = 5;
   public static final int DEFAULT_DISTANCE = 5;
   public static final String DEFAULT_NAME = "John Smith";
   
   // define variables
   private char gender;
   private char searchGender;
   private int romance;
   private int finance;
   private int distance;
   private String name;
   
   //constructors
   public DateProfile()
   {
      gender = DEFAULT_GEND;
      searchGender = DEFAULT_SEARCH_GEND;
      romance = DEFAULT_ROMANCE;
      finance = DEFAULT_FINANCE;
      distance = DEFAULT_DISTANCE;
      name = DEFAULT_NAME;
   }
   
   public DateProfile(char gender, char searchGender, int romance, int finance,
         int distance, String name)
   {
      setGender(gender);
      setSearchGender(searchGender);
      setRomance(romance);
      setFinance(finance);
      setDistance(distance);
      setName(name);
   }
   // mutators and accessors
   public char getGender()
   {
      return gender;
   }
   public boolean setGender(char gender)
   {
      if (gender == 'M' || gender == 'F') {
         this.gender = gender;
         return true;
      } else {
         this.gender = DEFAULT_GEND;
         return false;
      }
   }
   public char getSearchGender()
   {
      return searchGender;
   }
   public boolean setSearchGender(char searchGender)
   {
      if (searchGender == 'M' || searchGender == 'F') {
         this.searchGender = searchGender;
         return true;
      } else {
         this.searchGender = DEFAULT_SEARCH_GEND;
         return false;
      }
      
   }
   public int getRomance()
   {
      return romance;
   }

   public boolean setRomance(int romance)
   {
      if (isInRange(romance, MIN_ROMANCE, MAX_ROMANCE)) {
         this.romance = romance;
         return true;
      } else {
         this.romance = DEFAULT_ROMANCE;
         return false;
      }
      
   }
   public int getFinance()
   {
      return finance;
   }
   public boolean setFinance(int finance)
   {
      if (isInRange(finance, MIN_FINANCE, MAX_FINANCE)) {
         this.finance = finance;
         return true;
      } else {
         this.finance = DEFAULT_FINANCE;
         return false;
      }
   }
   public int getDistance()
   {
      return distance;
   }
   public boolean setDistance(int distance)
   {
      if (isInRange(distance, MIN_DISTANCE, MAX_DISTANCE)) {
         this.distance = distance;
         return true;
      } else {
         this.distance = DEFAULT_DISTANCE;
         return false;
      }      
   }
   public String getName()
   {
      return name;
   }
   public boolean setName(String name)
   {
      if (validName(name)) {
         this.name = name;
         return true;
      } else {
         this.name = DEFAULT_NAME;
         return false;
      }
   }
   
   // check for valid score value
   private static boolean isInRange(int value, int minValue, int maxValue)
   {
      return value >= minValue && value <= maxValue;
   }
   
   // check for valid name length
   private static boolean validName( String str ) {
      if (str != null && str.length() <= MAX_NAME_LEN && str.length() >= 
            MIN_NAME_LEN) {
         return true;
      } else {
         return false;
      }
   }
   
   // average the scores
   public double fitValue(DateProfile partner) {
      double fitValue = determineGenderFit(partner);
      double fitValue2 = determineRomanceFit(partner) + 
            determineFinanceFit(partner) + determineDistanceFit(partner);
      if (fitValue == 0) {
         return fitValue;
      } else {
         return (fitValue2 / 3);
      }
   }
   
   // obtain gender fit value
   private double determineGenderFit(DateProfile partner) {
      double genderCompatibility;
      if (partner.gender == searchGender && gender == partner.searchGender) {
         genderCompatibility = 1;
      } else {
         genderCompatibility = 0;
      }
      return genderCompatibility;
   }
   
   // obtain romance fit value
   private double determineRomanceFit(DateProfile partner) {
      return calculateFit(partner.getRomance(), getRomance());
   }
   
   // obtain finance fit value
   private double determineFinanceFit(DateProfile partner) {
      return calculateFit(partner.getFinance(), getFinance());
   }
   
   // obtain distance fit value
   private double determineDistanceFit(DateProfile partner) {
      return calculateFit(partner.getDistance(), getDistance());
   }
   
   // calculation of individual fit values
   private double calculateFit(double partnerValue, double inherentValue)
   {
      double calculateFit = 1 - (Math.abs(partnerValue - inherentValue) * 0.1);
      return calculateFit;
   }
   
   // print names and fit values
   public static void displayTwoProfiles(DateProfile profile1, 
         DateProfile profile2)
   {
      double datingScore = profile1.fitValue(profile2);
      System.out.println("Fit between " + profile1.name + " and " + 
      profile2.name + ": " + datingScore);
      
   }
   
}

/* ------------------------------ run ---------------------------------------

Fit between Test Run and Test Run: 0.0
Fit between Test Run and Lab Coat: 0.6999999999999998
Fit between Test Run and Mix Match: 0.0
Fit between Test Run and Spill Water: 0.0

Fit between Lab Coat and Test Run: 0.6999999999999998
Fit between Lab Coat and Lab Coat: 0.0
Fit between Lab Coat and Mix Match: 0.0
Fit between Lab Coat and Spill Water: 0.0

Fit between Mix Match and Test Run: 0.0
Fit between Mix Match and Lab Coat: 0.0
Fit between Mix Match and Mix Match: 1.0
Fit between Mix Match and Spill Water: 0.0

Fit between Spill Water and Test Run: 0.0
Fit between Spill Water and Lab Coat: 0.0
Fit between Spill Water and Mix Match: 0.0
Fit between Spill Water and Spill Water: 1.0

-------------------------------------------------------------------------- */
