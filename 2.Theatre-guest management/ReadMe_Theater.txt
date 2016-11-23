Theater guest management

I have implemented a simple ticket service that facilitates the discovery, temporary hold,
and final reservation of seats within a high-demand performance theater.

Most of the documentation is provided in each function defination.

Main class to consider is TicketServiceImpl for specific functionality asked.
Other are POJO classes and test class to test the functionalities.

Assume that the theater has a stage and one level of seating, as such:

````
        ----------[[  STAGE  ]]----------
        ---------------------------------
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss

###Here group of guests means the family or group that come together whose tickets are hold together.

Seat: represents the class for Seat objects in threater.
SeatHold: represents the class for Seat objects that are held.

State of seat:
FREE----->Hold---->RESERVE/EXPIRE

1. **Seat holds expire.**  After some period of time, held seats that are not reserved are returned to the pool of available seats.
2. **Seats are assigned together.** Seats and rows are numbered. Seats are held and reserved in blocks.
3. Functionality for finding and holding seats for guests is so written that guests are provided with the seats
   that are adjacent to each other as much as possible. 

For testing this, Separate class named TicketServiceImplTest.java is written. I have used the Junit test .
