# PingSweep
Simple ping scan connected to mysql database for longterm usage and statistics.
Starts http server on 8081 for data access.

localhost:8081/api/ping
localhost:8081/api/ping/"ip"
localhost:8081/api/activity
localhost:8081/api/activity/"ip"
localhost:8081/api/activity/week?weeknum="{weeknum}" //Get activity of week before today
localhost:8081/api/activity/lastweek

Activity get formated for future usage in app for easy visualisation per user days in week and hour.

Only successful pings and nameList for aliases are stored in db. Statistics calculated on request.

Not suteable for specific deployment without major changes in code. Config accesability is not priority for initial purpose of this SW.
SW is provided "AS IS", without warranty of any kind expressed or ipmlied and should be handled as such. Developed for educational purposes and sould not be viwed as template for real life aplication.
