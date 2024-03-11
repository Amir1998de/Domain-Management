
# ${shopName}  Config

<VirtualHost *:8082>
    ServerName ${shopDomain}

 	ProxyPass /resources/ http://localhost:6080/Test/resources/
    ProxyPass / http://localhost:6080/Test/shop/${shopId}
    ProxyPassReverse / http://localhost:6080/Test/shop/${shopId}
</VirtualHost>
 