# Vertx Micrometer Memory Leak
This project is used to reproduce a memory leak when using micrometer and influx in a vert.x project.

This project no longer reproduces the memory leak since updating the libraries that have been fixed. 

## Requirements
 * Java 11
 * gradle (gradlew command should download this for you)
 * Docker Compose (for running influxdb and grafana)
 * VisualVM (https://visualvm.github.io/)
 
## Running and Validating leak
 * Run influxdb and grafana with `docker-compose up -d`
 * Build the project with `./gradlew shadowJar`
 * Run the jar with `java -jar build/libs/memoryleak-1.0-SNAPSHOT-fat.jar start`
 * Run `curl http://localhost:8080/` to validate it is running. This should return `Hello world`.
 
Once the jar is running you can use apache benchmark (ab on a mac) to submit requests. 
```bash
ab -n 5000 -c 100 http://localhost:8080/
```
This command will submit 5000 requests using 100 concurrent threads. Run this command a few times to chew up some 
memory.

Open up VisualVM and connect to the memoryleak process. 
 * Take a heap dump and it should open in a new tab
 * Change the `Summary` Drop Down to `Objects`
 * Change the `All Objects` Drop down to `Dominators`
 * Wait for it to calculate the dominators
 
You should see that `io.micrometer.influx.InfluxMeterRegistry` is the largest dominator.

## Screenshots
There are two screenshots included in the screenshots folder of the heap dumps in VisualVM. 

The first one was taken after running the ab command a few times. 

The second one was taken after running the ab command a few more times.

After running garbage collect manually, the memory still does not get cleaned up. It just continues to grow as you make 
more requests.
