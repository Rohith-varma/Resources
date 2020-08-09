package ca.rohith.bigdata.kafka

case class Trip(route_id: Int,
                service_id: String,
                trip_id: String,
                trip_headsign: String,
                direction_id: String,
                shape_id: String,
                wheelchair_accessible: Int,
                note_fr: Option[String],
                note_en: Option[String])



