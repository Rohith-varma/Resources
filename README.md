# Streaming-Data-into-pipeline-in-near--realtime-using-Kafka-Spark
This repository contains the recource files that are necessary for the project
The objective is to be able to have data in a platform to run streaming data pipeline.
In this ,we 
• produce the content of a CSV file to a Kafka topic,
• consume messages from a Kafka topic

Produce trips.txt file to Kafka using kafka-console-producer. Each line is one message.
Consume the trip topic into your application 
Parse each record polled from Kafka into a Trip object 
Instantiate an object of EnrichedTrip for each message (Leave Route and Calendar part empty; None)
Convert each EnrichedTrip to CSV format and produce it to enriched_trip topic
