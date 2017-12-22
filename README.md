M800 SMS-API Sample Code
Copyright 2014 M800 www.m800.com


THIS M800 SOFTWARE IS PROVIDED BY M800 ON AN "AS IS" BASIS AND WITHOUT
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL M800 BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, IN ANY CIRCUMSTANCES, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR ITS USE.

For more infomration, please visit http://www.m800.com (General T&C)




Java REST API for M800's online platform.
=========================================

This API allows Java developers to easily connect to M800's online
platform. It handles all of the low-level tasks such as generating and
signing requests, connecting to the server, and parsing server
responses.

Requirements
------------
* Java 7+
* Faster XML Jackson 2.+
* Jersey 2.+
* Google Guava 18.0

Usage
-----
To use the API, include m800-api-client-x.x.x.jar as well as the above
required libraries to your project's classpath.

In order to use the API, you need to construct an instance of the
M800ApiClient class, passing in an instance of M800Config. This class
contains the parameters used to connect to the server.

```
M800Config config = new M800Config("<key>",
                                   "<secret>",
                                   new URI("http://host:port"));
M800SmsApi api = new M800SmsApiClient(config);
```

Where host is the hostname or IP address of M800's online platform API
server (i.e. api.m800.com), port is the IP port number used to connect
to the server (generally 80 for HTTP and 443 for HTTPS), key is the
developer's unique key, and the secret is the shared secret matching to
the developer key that you are using.

After you have created your M800ApiClient object, you can use the
methods on the object to communicate with M800's online platform. For
instance, to send a text SMS message:

```
SmsServerResponse response = api.sendTextSms("<sender's number>",
                                             "<recipient's number>",
                                             "normal",
                                             "text SMS through M800");
```

The sendTextSms method will return a SmsServerResponse object which you
can use to inspect the results returned from the server.

Source Code
-----------
To build the m800-api-client-x.x.x.jar from source, use the 'build'
target using the supplied gradle wrapper.

```
./gradlew build
```

License
-------

M800 SMS-API Sample Code
Copyright 2014 M800 www.m800.com


THIS M800 SOFTWARE IS PROVIDED BY M800 ON AN "AS IS" BASIS AND WITHOUT
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND`# FITNESS FOR A PARTICULAR
PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL M800 BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, IN ANY CIRCUMSTANCES, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR ITS USE.

For more infomration, please visit <http://www.m800.com> (General T&C)
