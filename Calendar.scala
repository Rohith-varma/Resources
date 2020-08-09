package ca.rohith.bigdata.kafka

case class Calendar(service_id: String,
                    monday: Int,
                    tuesday: Int,
                    wednesday: Int,
                    thursday: Int,
                    friday: Int,
                    saturday: Int,
                    sunday: Int,
                    start_date: String,
                    end_date: String)
