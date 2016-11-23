package com.skydance;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * A ticket service implementation.
 */
public class TicketServiceImpl implements TicketService {

	private int seatsAvailable;
	private int seatsReserved;
	private Map<String, SeatHold> seatHoldMap = new HashMap<>();

	private Map<Integer, Seat> seats;

	private Theatre theatre;

	public TicketServiceImpl(Theatre theatre) {
		seatsAvailable = theatre.getMaxSeats();
		seats = theatre.getSeatArrangements();
		this.theatre = theatre;
	}

	@Override
	public int numSeatsAvailable() {
		return seatsAvailable;
	}

	public int numSeatsReserved() {
		return this.seatsReserved;
	}

	/**
	 * If there are not available ids, this means we did not find any seats
	 * together, so we drop the holding for now. Then we will create a seat hold
	 * with ids, and mark the seats in the theater as not available. It is
	 * synchronised to make the thread safe
	 */
	@Override
	public synchronized Optional<SeatHold> findAndHoldSeats(int numSeats) {
		Optional<SeatHold> optionalSeatHold = Optional.empty();

		if (seatsAvailable >= numSeats) {
			String holdId = generateId();
			int[] seatids = theatre.getConsecutiveSeatsIds(numSeats);
			if (seatids.length > 0) {

				SeatHold seatHold = new SeatHold(holdId, numSeats, seatids);
				optionalSeatHold = Optional.of(seatHold);
				seatHoldMap.put(holdId, seatHold);
				seatsAvailable -= numSeats;
				theatre.markSeatUnavailable(seatids);
			} else {

				ArrayList<Integer> groupSeats = new ArrayList<>();
				int createdSeats = 0;
				int sizeOfSeatInGroup = numSeats - 1;
				while (createdSeats <= numSeats && sizeOfSeatInGroup > 0) {
					int[] tmpseats = theatre.getConsecutiveSeatsIds(sizeOfSeatInGroup);
					if (tmpseats.length > 0) {
						createdSeats += tmpseats.length;
						for (int y : tmpseats) {
							groupSeats.add(y);
						}
					} else {
						sizeOfSeatInGroup--;
					}

				}

				if (groupSeats.size() == numSeats) {
					SeatHold seatHold = new SeatHold(holdId, numSeats, seatids);
					optionalSeatHold = Optional.of(seatHold);
					seatHoldMap.put(holdId, seatHold);
					seatsAvailable -= numSeats;
					int[] tmp = groupSeats.stream().mapToInt(it -> it).toArray();
					theatre.markSeatUnavailable(tmp);
				}
			}

		}

		return optionalSeatHold;
	}

	@Override
	public synchronized Optional<String> reserveSeats(String seatHoldId) {
		Optional<String> optionalReservation = Optional.empty();
		;
		SeatHold seatHold = seatHoldMap.get(seatHoldId);
		if (seatHold != null) {
			int[] reservedSeatgroup = seatHold.getSeats();
			if (reservedSeatgroup.length > 0) {
				seatsReserved += seatHold.getNumSeats();
				optionalReservation = Optional.of(seatHold.getId());
				seatHoldMap.remove(seatHoldId);
				theatre.markSeatReserved(reservedSeatgroup);
			}

		}

		return optionalReservation;
	}

	private String generateId() {
		return UUID.randomUUID().toString();
	}

}
