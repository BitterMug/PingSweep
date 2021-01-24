# PingSweep
Simple ping scan connected to mysql database for longterm usage and statistics.
Starts http server on 8081 for data access.

localhost:8081/api/ping
localhost:8081/api/ping/"ip"
localhost:8081/api/activity
localhost:8081/api/activity/"ip"

Activity get formated for future usage in app for easy visualisation per user days in week and hour.

Only successful pings and nameList for aliases are stored in db. Statistics calculated on request.
