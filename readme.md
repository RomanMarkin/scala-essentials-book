# How to fix sbt exception ' PKIX path building failed'
The reason of the problem: 

Used java keystore (probably $JAVA_HOME/jre/lib/security/cacerts) doesn't contain SSL certificate chain needed to connect remote host by HTTPS.

How to fix: https://confluence.atlassian.com/kb/how-to-import-a-public-ssl-certificate-into-a-jvm-867025849.html

### Step 1: Download and transform SSL certificate of needed host

For example, from the exception stack trace we found that java can't connect to host repo.typesafe.com (Attention: you should replace hostname in the command two times):  
`$ cd ~/Downloads`  
`$ openssl s_client -connect repo.typesafe.com:443 -servername repo.typesafe.com:443 < /dev/null | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > public.crt`
 
Double check your downloaded certificate looks like this:  
`$ cat public.crt`   
```
-----BEGIN CERTIFICATE-----
MIIICDCCBvCgAwIBAgIQI+8+biCLxARQRVOeAgrLWzANBgkqhkiG9w0BAQsFADCB
jzELMAkGA1UEBhMCR0IxGzAZBgNVBAgTEkdyZWF0ZXIgTWFuY2hlc3RlcjEQMA4G
...
o/ms7Qy8Lf0OQqThw4UkTpsY4CL0CgbW7R3McKxe4ddJZ9SNi8beeZY93FUPRIYl
HtFOxCeMCkDbiEEQC/YasSMivU5zjCPVJhme2BuZwM+F3hKZwmFB84WZL+A=  
-----END CERTIFICATE-----
```

### Step 2: Import SSL certificate into default Java keystore

```
<JAVA_HOME>/bin/keytool -import -alias <server_name> -keystore <JAVA_HOME>/jre/lib/security/cacerts -file public.crt
```

To find JAVA_HOME on macOS use:  
`which java`  

Example: 
```
$ sudo /usr/bin/keytool -import -alias repo.typesafe.com -keystore /Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/security/cacerts -file public.crt  

...some text there...

Trust this certificate? [no]:  yes
Certificate was added to keystore
```
Then enter the password if prompted (the default is 'changeit').

