#!C:\cygwin64\bin\bash.exe
java -Dcgi.query_string=$QUERY_STRING -Dcgi.request_method=$REQUEST_METHOD -jar SignIn.jar $HTTP_COOKIE
