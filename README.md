## Java Web Services with Spring Boot and MySQL ##
Learning Java web services with Spring. Screenshots and example of codes are provided. I hope it'll be help.



## Prerequisites ##
- Windows - version 10
- [Java](https://java.com) - version 1.8.0
- [Eclipse IDE for Java Developers](http://www.eclipse.org/) - version 4.8.0
- [Buildship Gradle Integration to Eclipse](https://projects.eclipse.org/projects/tools.buildship) - version 2.0
- [Gradle](https://gradle.org/) - version 4.8.1
- [Spring projects](https://start.spring.io/) - version 2.0.3



## Step Preparing Prerequisites ##

### Install Buildship Gradle Integration to Eclipse ###
Step to install Buildship Gradle Integration:
- Click Help
- Click Eclipse Marketplace
- Search Buildship Gradle Integration on Find form
- Click Install button
- Read their terms of license aggrement and choose I accept, then click finish
- Please wait Eclipse finish installing process, then restart Eclipse 


### Install Gradle ###
Step to install and setup manually:
- Go to [Gradle's release page](https://gradle.org/releases/)
- Download latest version. You can choose binary format or complete format
```
Different Gradle binary format and complete format
>gradle-x.xx-all.zip file will have binaries, sources and documentation, gradle-x.xx-bin.zip will have only binaries 
(That should be enough as you dont need any samples/docs)
© copied from San
```
- Extract zip file and copy-paste to your folder, for example C:\Program Files\Gradle
- Open Control Panel to starting add the environment variable GRADLE_HOME
- Click System and Security
- Click System 
- Click Advanced system settings
- Choose Advanced tab 
- Click Environment Variables button at bottom windows
- In the section System Variables, click New...
- Add GRADLE_HOME on field Variable name
- On field Variable value add Gradle path, for example C:\Program Files\Gradle\gradle-4.8.1
- Click Ok
- Find Variable PATH and click Edit…
- Check if Gradle path added or not. If it is, close Edit environment variable window, click OK on Environment Variables window, click OK on System Properties window
- If on Variable PATH doesn’t have Gradle path, click New, add Gradle bin path for example C:\Program Files\Gradle\gradle-4.8.1\bin, close all remaining windows by clicking OK
- Restart command prompt
- Type gradle -f to check if Gradle is properly installed. Example of result
```gradle
------------------------------------------------------------
Gradle 4.8.1
------------------------------------------------------------

Build time:   2018-06-21 07:53:06 UTC
Revision:     0abdea078047b12df42e7750ccba34d69b516a22

Groovy:       2.4.12
Ant:          Apache Ant(TM) version 1.9.11 compiled on March 23 2018
JVM:          1.8.0_171 (Oracle Corporation 25.171-b11)
OS:           Windows 10 10.0 amd64
```

You can install with a package manager. Go check [their website](https://gradle.org/install/)


### Generate Spring Boot ###
- Choose Grandle Project
- Choose Java
- Choose Spring Boot version 2.0.3
- Fill Group field. For example com.example
- Fill Artifact field. For example webservice
- Choose Dependencies. You can search dependencies, for now I just use MySQL 
- Click Generate Project

![Image of Generate Spring Boot](https://github.com/laksmisetiawati/java-web-services-with-springboot-and-mysql/blob/master/img/Generate-Spring-Boot.JPG)


### Starting Create Project ###
1. Extract generated spring project folder, my folder named 'webservice'
2. Copy-paste 'webservice' into eclipse workspace location, in my case F:\eclipse-workspace
3. Open Projects from File System...
4. Choose directory, in my case F:\eclipse-workspace\webservice, click finish

	![Image of Choose directory existing project](https://github.com/laksmisetiawati/java-web-services-with-springboot-and-mysql/blob/master/img/import-projects.jpg)

5. Create new package com.link.webservice.entity under src/main/java
6. Create POJO (Plain Old Java Object) Alamat.java on package com.link.webservice.entity
	
	```Java
	package com.link.webservice.entity;

	@Entity
	@Table(name="alamat")
	@Data

	public class Alamat {

		@Id
	 	@GeneratedValue(generator="uuid")
	 	@GenericGenerator(name = "uuid", strategy = "uuid2")

	 	private String id;

	 	@Column(nullable=false)
	 	private String jalan;

	 	@Column(nullable=false)
	 	private String kota;

	 	@Column(nullable=false)
	 	private String propinsi;

	 	@Column(nullable=false, length=5)
	 	private int kodepos;
	}
	```


---


### Error Handling ###

- The import org.springframework cannot be resolved
file: WebserviceApplication.java
package: com.link.webservice
line: 3 import org.springframework.boot.SpringApplication;
how to fix
1. Right click project, click import...

	![Image of The import org.springframework cannot be resolved 1](https://github.com/laksmisetiawati/java-web-services-with-springboot-and-mysql/blob/master/img/error-fix-a-1.jpg)

2. Choose Gradle > Existing Gradle Project, click next

	![Image of The import org.springframework cannot be resolved 1](https://github.com/laksmisetiawati/java-web-services-with-springboot-and-mysql/blob/master/img/error-fix-a-2.jpg)

3. If you see welcome page, keep click next, if not just skip this step
4. Browse current project root directory, in my case F:\eclipse-workspace\webservice
5. Click finish

	![Image of The import org.springframework cannot be resolved 1](https://github.com/laksmisetiawati/java-web-services-with-springboot-and-mysql/blob/master/img/error-fix-a-3.jpg)




---


**Notes**
- Please moved all files from webservice into parent path


---


**Not Fixed yet. I'll Update later**