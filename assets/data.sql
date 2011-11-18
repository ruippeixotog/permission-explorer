INSERT INTO permissions (name,common,description) VALUES ('android.permission.READ_CONTACTS',1,'This 
permission is of high importance. Unless an app explicitly states a specific feature that it would 
use your contact list for, there isn’t much of a reason to give an application this permission. The 
one exception to that rule includes typing or note taking applications and/or quick-dial type applications. 
Those might require your contact information to help make suggestions to you as you type. 
Typical application that require this permission include: social networking apps, typing/note taking apps, 
SMS replacement apps, contact management apps.');
INSERT INTO permissions (name,common,description) VALUES ('android.permission.SEND_SMS',1,'This permission 
is of moderate to high importance. This could let an application send an SMS on your behalf, and much like 
the phone call feature above, it could cost you money. Certain SMS numbers work much like 1-900 numbers and 
automatically charge your phone company money when you send them an SMS.');
INSERT INTO permissions (name,common,description) VALUES ('android.permission.INTERNET',1,'This is a 
very important permission you will want to pay attention to. Many apps will request this but not all 
need it. For any malware to truly be effective it needs a means by which to transfer data off of your phone, 
this is one of the setting it would definitely have to ask for. However, in this day and age of cloud computing 
and always-on internet connectivity, many, many legitimate applications also request this. You will have to be 
very careful with this setting and use your judgment. It should always peak your interest to think about whether 
your application needs this permission. Typical applications that would use this include but are not limited to:
web browsers, social networking applications, internet radio, cloud computing applications, weather widgets.');
INSERT INTO permissions (name,common,description) VALUES ('android.permission.READ_PHONE_STATE',1,'This permission 
is of moderate to high importance. Unfortunately this permission seems to be a bit of a mixed bag. While it’s 
perfectly normal for an application to want to know if you are on the phone or getting a call, this permission 
also gives an application access to 3 unique numbers that can identify your phone. The numbers are the IMEI, 
IMSI and a 64 bit unique id that Google provides for your phone. Nevertheless, while this permission can be innocuous, it is 
one to keep a good watch on. An application can send this information over the internet unencrypted to a third party.');
INSERT INTO permissions (name,common,description) VALUES ('android.permission.CALL_PHONE',1,'This permission is of moderate 
to high importance. This could let an application call a 1-900 number and charge you money. Legitimate applications that use 
this include: Google voice.');
INSERT INTO permissions (name,common,description) VALUES ('android.permission.SEND_SMS',1,'This permission is of moderate to 
high importance. This could let an application send an SMS on your behalf, it could cost you money. Certain SMS numbers work 
much like 1-900 numbers and automatically charge your phone company money when you send them an SMS.');
INSERT INTO permissions (name,common,description) VALUES ('android.permission.WRITE_EXTERNAL_STORAGE',1,'This permission is 
of high importance. This will allow the applications to read, write, and delete anything stored on your phone’s SD card. 
This includes, pictures, videos, mp3s, and even data written to your SD card by other applications. However there are many 
legitimate uses for this permission. Many people want their applications to store data on the SD card, and any application 
that stores information on the SD card will need this permission. You will have to use your own judgment and be cautious with 
this permission knowing it is very powerful but very often used by legitimate applications. Applications that typically need
this permission include: camera applications, video applications, note taking apps, backup applications.');
INSERT INTO permissions (name,common,description) VALUES ('android.permission.ACCESS_FINE_LOCATION',1,'While not a danger 
for stealing any of your personal information, this will allow an application to track where you are. Anyone can know, where you 
typicall go out and determine where you live, work and have fun.Typical applications that might need this include 
 restaurant directories, movie theater finders, and mapping applications. ');
INSERT INTO permissions (name,common,description) VALUES ('android.permission.READ_CONTACTS',1,'This permission is of high 
importance. Unless an app explicitly states a specific feature that it would use your contact list for, there isn’t much of 
a reason to give an application this permission. The one exception to that rule includes typing or note taking applications 
and/or quick-dial type applications. Those might require your contact information to help make suggestions to you as you type. 
Typical application that require this permission include: social networking apps, typing/note taking apps, SMS replacement apps, 
contact management apps.');
INSERT INTO permissions (name,common,description) VALUES ('android.permission.WRITE_SETTINGS',1,'This permission is pretty important 
but only has the possibility of moderate impact. Global settings are pretty much anything you would find under Android’s main 
‘settings’ window. However there are a lot of these setting that are perfectly reasonable for an application to want to change. 
Typical applications that would use this include: Volume control widget, notifications, widgets, settings widgets.');
INSERT INTO permissions (name,common,description) VALUES ('android.permission.ACCESS_NETWORK_STATE',1,'This permission access the information
about your network. This way, the application knows if your network(wigi or mobile) is enabled or disabled. This permission is of low/moderate importance.');
INSERT INTO permissions (name,common,description) VALUES ('android.permission.CHANGE_WIFI_STATE',1,'This permission changes the state
of your wifi connection. In other words, if wifi is enabled it will be disabled and vice-versa. This permission is of low/moderate importance.');