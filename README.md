# Streaming-Data-into-pipeline-in-near--realtime-using-Kafka-Spark

#Run the KafkaProject file

We get the information of STM every day and need to run an ETL pipeline to enrich data for reporting
and analysis purpose in real-time. Data is split in two
1. A set of tables that build dimension (batch style)
2. Trips that needed to be enriched for analysis and reporting (streaming)
In order to be able to run streaming analysis with a platform such as Spark Streaming, we need to have
the records in a streaming platform such as Kafka.

Produce trips.txt file to Kafka using kafka-console-producer. Each line is one message.
Consume the trip topic into your application 
Parse each record polled from Kafka into a Trip object 
Instantiate an object of EnrichedTrip for each message (Leave Route and Calendar part empty; None)
Convert each EnrichedTrip to CSV format and produce it to enriched_trip topic
