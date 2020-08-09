package ca.rohith.bigdata.kafka

case class EnrichedTrip(trip: Trip,calendar: Option[Calendar],route: Option[Route])

object Convert{
  def toCsv(enrichedTrip: EnrichedTrip): String = {
      enrichedTrip.trip.route_id + "," +
      enrichedTrip.trip.service_id + "," +
      enrichedTrip.trip.trip_id + "," +
      enrichedTrip.trip.trip_headsign + "," +
      enrichedTrip.trip.direction_id + "," +
      enrichedTrip.trip.shape_id + "," +
      enrichedTrip.trip.wheelchair_accessible + "," +
      enrichedTrip.trip.note_en.getOrElse("") + "," +
      enrichedTrip.trip.note_fr.getOrElse("") + ",,,,,,,,,,,,,,,,,,"
  }
}
