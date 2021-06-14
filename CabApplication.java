/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabapplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author PriyaSekar
 */

class CabDetails
{
    String number;
    String driver;
    String type;
    String location;
    int costBasedOnCar;
   
    
    CabDetails(String number, String driver, String type, String location,int costBasedOnCar)
    {
     this.number = number;
     this.driver = driver;
     this.type = type;
     this.location = location;
     this.costBasedOnCar = costBasedOnCar;
     }
    
}
class User
{
    String name;
    String password;
    String mobileNumber;
    int count;
    User(String name,String password,String mobileNumber,int count)
    {
        this.name = name;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.count = count;
    }
    
}

class TripDetail
{
    LocalDate date;
    LocalTime time;
    String mail;
    String location;
    int price;
    String car;
    String driverName;
    TripDetail(LocalDate date, LocalTime time,String mail, String location, int price, String car, String driverName)
    {
        this.date = date;
        this.time = time;
        this.mail = mail;
        this.location = location;
        this.price = price;
        this.car = car;
        this.driverName = driverName;
    }
}
class DriverDetails
{
    String name;
    int age;
    String mobileNumber;
    DriverDetails(String name,int age, String mobileNumber)
    {
        this.name = name;
        this.age = age;
        this.mobileNumber = mobileNumber;
    }
}

public class CabApplication 
{
    public static final Scanner sc = new Scanner(System.in);
     HashMap<String,TripDetail> tripDetails = new HashMap();
     HashMap<String,Queue> availablityOfCarInLocations = new HashMap<>();
     HashMap<String,User> userDetails = new HashMap<>();
     HashMap<String,CabDetails> cabDetails = new HashMap<>();
     HashMap<String,Integer> costPerLocation = new HashMap<>();
     HashMap<String,ArrayList> userTravelHistory = new HashMap<>();
     HashMap<String,String> adminDetails = new HashMap<>();
     HashMap<String,DriverDetails> driverDetails = new HashMap<>();
     int i = 1;
    void addingCar()
    {
        System.out.println("Enter car number: ");
        String carNumber = sc.next().toUpperCase();
        System.out.println("Enter car type: ");
        String carType = sc.next().toLowerCase();
        System.out.println("Enter destination:");
        String destination = sc.next().toUpperCase();
        System.out.println("Enter cost for type"+carType);
        int costPerCar = sc.nextInt();
        System.out.println("Enter driver name:");
        String driverName = sc.next().toLowerCase();
        
        CabDetails addedCar = new CabDetails(carNumber,driverName,carType,destination, costPerCar);
        addingDriver(driverName);
        carAvailabilityInLocation(addedCar);
        cabDetails.put(carNumber,addedCar);
        System.out.println("Added Successfully");
        
    }
    void addingDriver(String name)
    {
       System.out.println("Enter driver mobile number");
        String driverMobileNumber = mobileNumberVerfication();
        System.out.println("Enter driver's age");
        int driverAge = sc.nextInt(); 
        DriverDetails addedDriver = new DriverDetails(name,driverAge,driverMobileNumber);
        driverDetails.put(name, addedDriver);
    }
    void carAvailabilityInLocation(CabDetails details)
    {
        Set carLocation = availablityOfCarInLocations.keySet();
        ArrayList carLocationList = new ArrayList(carLocation);
        for(int i=0;i<carLocationList.size();i++)
        {
            String location = (String) carLocationList.get(i);
          
            if (availablityOfCarInLocations.get(location).contains(details.number))
            {
                availablityOfCarInLocations.get(location).remove(details.number);
            }
        }
        if(availablityOfCarInLocations.containsKey(details.location))
        {
            availablityOfCarInLocations.get(details.location).add(details.number);
        }
        else
        {
            Queue carDetails = new LinkedList();
            carDetails.add(details.number);
            availablityOfCarInLocations.put(details.location, carDetails);
        }
    }
    void addingUserOrDetailUploading()
    {
        String verifiedMailId = mailVerification();
        String verifiedPassword =  passwordVerfication();
        String verifiedNumber = mobileNumberVerfication();
        User addingUser = new User(verifiedMailId,verifiedPassword,verifiedNumber,0);
        userDetails.put(verifiedMailId, addingUser);
        ArrayList userHistory = new ArrayList<>();
        userTravelHistory.put(verifiedMailId,userHistory);
        System.out.println("added successfully");
    }
  
    String mailVerification()
    {
        System.out.println("Enter mail ID");
        String mail = sc.next().toLowerCase();
        if (mail.matches("^(.+)@(.+)$"))
        {
           return mail;
        }else{
            System.out.println("Please enter valid email Id eg.user@gmail.com");
            mailVerification();
        }
        return null;
    }
    
    String passwordVerfication()
    {
        System.out.println("Enter password:");
        String password = sc.next();
        System.out.println("Confirm password:");
        String confirmPassword = sc.next();
        if (password.matches("^(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")  && password.equals(confirmPassword))
        {
            return password;
        }else{
             System.out.println("Please re-check your password(check length(min 8 & max 20 , one special Character) and confirm password)");
              passwordVerfication();
        }
        return null;
    }
    
    String mobileNumberVerfication()
    {
        System.out.println("Enter your ten digit mobile number:");
        String mobileNumber = sc.next();
        if (mobileNumber.matches("^[6-9]\\d{9}$"))
        {
            return mobileNumber;
        }else{
            System.out.println("Kindly check mobileNumber");
            mobileNumberVerfication();
        }
        return null;
    }
    
    void costDetermination()
    {
        System.out.println("Enter start area ");
        String startArea = sc.next().toUpperCase();
        System.out.println("Enter end area ");
        String endArea = sc.next().toUpperCase();
        System.out.println("Enter cost");
        int cost = sc.nextInt();
        costPerLocation.put(startArea+""+endArea, cost);
    }
    
    void printingLine(int number)
      {
          for(int i = 0;i<number;i++)
         {
             System.out.print("*");
         }
         System.out.println();
      }
    void booking(String mailEntered)
    {
        System.out.println("Enter starting destination");
        String startingDestination = sc.next().toUpperCase();
        System.out.println("Enter ending destination");
        String endingDestination = sc.next().toUpperCase();
        String destination = startingDestination+"-"+endingDestination;
        String formattedDestination = destination;
        if(!costPerLocation.containsKey(destination))
        {
            StringBuffer buffer = new StringBuffer(destination);
            buffer.reverse();
            String reversedArea = buffer.toString();
            formattedDestination = reversedArea;
        }
        if( availablityOfCarInLocations.get(startingDestination).size()>0)
        {
            String bookedCar = (String) availablityOfCarInLocations.get(startingDestination).peek();
            ArrayList<TripDetail> bookedCarList;
            CabDetails car = cabDetails.get(bookedCar);
//            if (!tripDetails.containsKey(bookedCar))
//            {
//                 bookedCarList = new ArrayList<TripDetail>();
//                 tripDetails.put(bookedCar, bookedCarList);
//             }
            availablityOfCarInLocations.get(endingDestination).add(bookedCar);
            LocalDate date = java.time.LocalDate.now();
            LocalTime time = LocalTime.now();
            int cost = cabDetails.get(bookedCar).costBasedOnCar*costPerLocation.get(formattedDestination);
            
            printingLine(25);
            System.out.format("%5s%10s","area","cost");
            System.out.println();
            printingLine(25);
            System.out.format("%5s%10s",destination,cost);
            System.out.println();
            printingLine(25);
            
            String id = "t"+String.valueOf(i);
            userDetails.get(mailEntered).count++;
            TripDetail trip = new TripDetail(date,time,mailEntered,destination,cost,bookedCar,car.driver);
            tripDetails.put(id, trip);
           
            userTravelHistory.get(mailEntered).add(id);
             i++;
            availablityOfCarInLocations.get(startingDestination).remove();
        }
        else
        {
            System.out.println("Car not available");
        }
        
    }
    
    void admin()
    {
        while (true)
        {
        System.out.println("Enter your choice \n1 (adding car) \n2 (viewing car availability) \n3 (car trip details) \n4(user details) \n5(viewing car details) \n6 (changing details of car) \n7 (cost determination) \n8 (Driver details)");
        int adminChoice = sc.nextInt();
            if(adminChoice == 1)
            {
                addingCar();
            }
        
             else if (adminChoice == 2)
            {
                 System.out.println(availablityOfCarInLocations); 
            }
            else if(adminChoice == 3)
            {
                 
                 
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
                 System.out.println("Enter starting date --> 2021-03-12 (year-month-day)");
                 String startDate = sc.next();
                 LocalDate formattedStartDate = LocalDate.parse(startDate, formatter);
                 System.out.println("Enter Ending date --> 2021-03-12 (year-month-day)");
                String endDate = sc.next();
                LocalDate formattedEndDate = LocalDate.parse(endDate, formatter);
                ArrayList tripId = new ArrayList(tripDetails.keySet());
                System.out.println("Enter car number");
                String carNumberEntered = sc.next().toUpperCase();
                printingLine(120);
                System.out.format("%5s%12s%17s%25s%30s%35s%38s","car number","Driver name","location","user mail","price","Time","Date");
                System.out.println();
                printingLine(120);
                
                for(int i=0;i<tripDetails.size();i++)
                     {
                     TripDetail carTrip = (TripDetail) tripDetails.get(tripId.get(i));
                     LocalDate date = carTrip.date;
                     
                    if (carTrip.car.equals(carNumberEntered))
                    {
                         if ((date.isAfter(formattedStartDate) && date.isBefore(formattedEndDate)) || ((date.isEqual(formattedStartDate) || date.isEqual(formattedEndDate))))
                         {
                         
                             System.out.format("%5s%12s%17s%25s%30s%35s%38s",carTrip.car, carTrip.driverName, carTrip.location,carTrip.mail,carTrip.price,carTrip.time,carTrip.date);
                             System.out.println();
                        }
                    }
                }
                 printingLine(120);
            }
            
             else if(adminChoice == 4)
             {
                 ArrayList userNameList = new ArrayList (userDetails.keySet());
                 printingLine(50);
                 System.out.format("%5s%15s%17s","userName","mobile number","count");
                 System.out.println();
                 printingLine(50);
                 for(int i=0;i<userDetails.size();i++)
                 {
                     System.out.format("%5s%12s%17s",userDetails.get(userNameList.get(i)).name,userDetails.get(userNameList.get(i)).mobileNumber,userDetails.get(userNameList.get(i)).count);
                     System.out.println();
                 }
                 printingLine(50);
             }
             else if (adminChoice == 5)
             {
                 ArrayList cabList = new ArrayList ( cabDetails.keySet());
                 printingLine(90);
                 System.out.format("%5s%12s%17s%24s%30s","car number","driver","car type","location","cost");
                 System.out.println();
                 printingLine(90);
                 for (int i=0; i<cabDetails.size(); i++)
                 {
                     System.out.format("%5s%12s%17s%24s%30s",cabDetails.get(cabList.get(i)).number, cabDetails.get(cabList.get(i)).driver , cabDetails.get(cabList.get(i)).type ,cabDetails.get(cabList.get(i)).location , cabDetails.get(cabList.get(i)).costBasedOnCar);
                     System.out.println();
                 }
                 printingLine(90);
             }
             else if(adminChoice ==  6)
             {
                 System.out.println("Enter car number");
                 String carNumber = sc.next().toUpperCase();
                 if (cabDetails.containsKey(carNumber))
                 {
                     System.out.println("Enter 1(driver changing) 2(type changing) 3(cost changing) 4(area changing)");
                     int choiceForChangingDetail = sc.nextInt();
                     if(choiceForChangingDetail == 1)
                     {
                         System.out.println("Enter new driver name");
                         String newDriverName = sc.next().toLowerCase();
                         cabDetails.get(carNumber).driver = newDriverName;
                         if (!driverDetails.containsKey(newDriverName))
                         {
                             addingDriver(newDriverName);
                         }
                     }
                     else if (choiceForChangingDetail == 2)
                     {
                         System.out.println("Enter cost");
                         int newCost = sc.nextInt();
                         cabDetails.get(carNumber).costBasedOnCar = newCost;
                     }
                     else if(choiceForChangingDetail == 3)
                     {
                         System.out.println("Enter new car type");
                         String newCarType = sc.next();
                         cabDetails.get(carNumber).type = newCarType; 
                     }
                     else if(choiceForChangingDetail == 4)
                     {
                         System.out.println("Enter new area");
                         String newArea = sc.next().toUpperCase();
                         cabDetails.get(carNumber).location = newArea;
                         carAvailabilityInLocation(cabDetails.get(carNumber));
                     }
                     
                 }
                
                 else
                 {
                     System.out.println("oops! not available");
                     System.out.println("Would you like to add (y/n)");
                     String choiceForAdding = sc.next().toLowerCase();
                     if (choiceForAdding.equals("y"))
                     {
                         addingCar();
                     }
                 }
             }
             else if (adminChoice == 7)
                 {
                     costDetermination();
                 }
             else if(adminChoice == 8)
             {
                 System.out.println("Enter driver name");
                 String enteredName = sc.next().toLowerCase();
                 printingLine(25);
                 System.out.format("%5s%10s%15s","name","age","mobile number");
                 System.out.println();
                 printingLine(25);
                 System.out.format("%5s%10d%15s",driverDetails.get(enteredName).name,driverDetails.get(enteredName).age,driverDetails.get(enteredName).mobileNumber);
                 System.out.println();
                 printingLine(25);
                 
             }
             else
             {
                 break;
             }
        }
     }
                     

    
    
    public static void main(String[] args) 
    {
        CabApplication carApp = new CabApplication();
        carApp.adminDetails.put("admin1", "admin@123");
        CabDetails addedCar = new CabDetails("TN29AA0000","driver1","mini","A",10);
       Queue carDetails = new LinkedList();
       carDetails.add(addedCar.number);
        carApp.availablityOfCarInLocations.put(addedCar.location, carDetails);
        carApp.cabDetails.put(addedCar.number,addedCar);
        addedCar = new CabDetails("TN29AA0001","driver2","mini","B",10);
        carDetails = new LinkedList();
        carDetails.add(addedCar.number);
        carApp.availablityOfCarInLocations.put(addedCar.location, carDetails);
        carApp.cabDetails.put(addedCar.number,addedCar);
        addedCar = new CabDetails("TN29AA0002","driver3","mini","C",10);
        carDetails = new LinkedList();
        carDetails.add(addedCar.number);
        carApp.availablityOfCarInLocations.put(addedCar.location, carDetails);
        carApp.cabDetails.put(addedCar.number,addedCar);
        carApp.costPerLocation.put("A-B", 60);
        carApp.costPerLocation.put("A-C",45);
        carApp.costPerLocation.put("B-C", 50);
        User detail = new User("user1@gmail.com","user1@gmail","9874563210",0);
        carApp.userDetails.put(detail.name, detail);
        DriverDetails addedDriver = new DriverDetails("driver1",45,"9874563100");
        carApp.driverDetails.put(addedDriver.name, addedDriver);
        addedDriver = new DriverDetails("driver2",35,"7896541230");
        carApp.driverDetails.put(addedDriver.name, addedDriver);
        addedDriver = new DriverDetails("driver3",37,"7896451230");
        carApp.driverDetails.put(addedDriver.name, addedDriver);
        ArrayList travelHistory = new ArrayList();
        carApp.userTravelHistory.put("user1@gmail.com", travelHistory);
        while(true)
        {
            System.out.println("Enter \n1 (admin) \n2 (customer) \n3(break)");
            int userSelection = sc.nextInt();
            if(userSelection == 1)
            {
                carApp.admin();
            }
            else if(userSelection == 2)
            {
                System.out.println("Enter \n1 (sign up) \n2 (Log in) \n3 (Exit)");
                int userChoice = sc.nextInt();
                if (userChoice == 1)
                {
                    carApp.addingUserOrDetailUploading();
                }
                else if(userChoice == 2)
                {
                    System.out.println("Enter mail id");
                    String mailEntered = sc.next().toLowerCase();
                    System.out.println("Enter password");
                    String passwordEntered = sc.next();
                    if(carApp.userDetails.containsKey(mailEntered))
                    {
                         if (passwordEntered.equals(carApp.userDetails.get(mailEntered).password))
                         {
                             System.out.println("Successfully logged in");
                             while (true)
                             {
                                 System.out.println("Enter \n1(booking) \n2(history) \n3(changing password) \n4(changing mobile number) \n5 (exit)");
                                 int choiceAfterLoggedIn = sc.nextInt();
                                 if (choiceAfterLoggedIn == 1)
                                 {
                                     carApp.booking(mailEntered);
                                 }
                                 else if(choiceAfterLoggedIn == 2)
                                 {
                                   //  System.out.println(carApp.userTravelHistory);
                                     ArrayList tripHistory = new ArrayList(carApp.userTravelHistory.get(mailEntered)); //t1,t4
                                     System.out.println("Enter timing (eg-> 2021-06-13)");
                                     String dateEntered = sc.next();
                                     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
                                     LocalDate formattedDate= LocalDate.parse(dateEntered, formatter);
                                     carApp.printingLine(90);
                                     System.out.format("%5s%15s%17s%24s%27s%31s","mail","car","location","driver name","price","Time");
                                     System.out.println();
                                     carApp.printingLine(90);
                                     
                                     for(int i=0;i<tripHistory.size();i++)
                                     {
                                         //System.out.println("inside for");
                                         if(carApp.tripDetails.get(tripHistory.get(i)).date.isEqual(formattedDate))
                                         {
                                             //System.out.println("inside if");
                                             System.out.format("%5s%15s%17s%24s%27s%31s",carApp.tripDetails.get(tripHistory.get(i)).mail,carApp.tripDetails.get(tripHistory.get(i)).car,carApp.tripDetails.get(tripHistory.get(i)).location,carApp.tripDetails.get(tripHistory.get(i)).driverName,carApp.tripDetails.get(tripHistory.get(i)).price,carApp.tripDetails.get(tripHistory.get(i)).time);
                                             System.out.println();
                                         }
                                     }
                                     carApp.printingLine(90);
                            
                                 }
                                 else if(choiceAfterLoggedIn == 3)
                                 {
                                    String newPassword = carApp.passwordVerfication();
                                    carApp.userDetails.get(mailEntered).password = newPassword;   
                                 }
                                 else if (choiceAfterLoggedIn == 4)
                                 {
                                    String newMobileNumber = carApp.mobileNumberVerfication();
                                    carApp.userDetails.get(mailEntered).mobileNumber = newMobileNumber;
                                 }
                             
                                 else
                                 {
                                     break;
                                 }
                             }
                         }
                         else
                         {
                             System.out.println("Please re check your password");
                         }
                    }
                    else
                    {
                        System.out.println("please sign in");
                    }
                }
                else
                {
                    break;
                }
            }
            else
            {
                break;
            }
        }
    }
}
