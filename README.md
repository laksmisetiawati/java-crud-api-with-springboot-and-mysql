## Prerequisites ##
- Windows - version 10
- [Java](https://java.com) - version 1.8.0
- [Eclipse IDE for Java Developers](http://www.eclipse.org/) - version 4.8.0
- [Buildship Gradle Integration to Eclipse](https://projects.eclipse.org/projects/tools.buildship) - version 2.0
- [Project Lombok](https://projectlombok.org/) - version 1.18.0
- [Gradle](https://gradle.org/) - version 4.8.1
- [Spring projects](https://start.spring.io/) - version 1.5.14

## Step ##
- copy generated project dari Spring Boot ke workspace eclipse
- Open Projects from File System... terus pilih folder
- update build.gradle dengan menambahkan `compile('org.springframework.boot:spring-boot-starter-data-jpa')` di dependencies{ }
- run gradle eclipse dari command prompt untuk mengimport project sebagai gradle 
- gradle eclipse harus selalu di run setiap ada update build.gradle
- install lombok, pilih eclipse sebagai IDE ketika install
- update build.gradle dengan menambahkan `compileOnly "org.projectlombok:lombok:1.16.16"` di dependencies { }
- buat package `id.co.hanoman.training.webservices.entity`
- buat POJO `Alamat.java`
    ```java
    package id.co.hanoman.training.webservices.entity;

    import javax.persistence.Column;
    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.Id;
    import javax.persistence.Table;

    import org.hibernate.annotations.GenericGenerator;

    import lombok.Data;

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
- setup configuration database di application.properties 
    ```java
    spring.datasource.url=jdbc:mysql://localhost:3306/latihan
    spring.datasource.username=root
    spring.datasource.password=admin
    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
    ```
- tambahkan `spring.jpa.generate-ddl=true` di application.properties untuk menjalankan proses pembuatan table 'alamat' dari file entity Alamat.java
- buat file `import.sql` di folder `src/main/resource`
    ```sql
    insert into alamat (id, jalan, kota, propinsi, kodepos) values ('1b','Jalan Penggilingan','Cakung','Jakarta Timur',13940);
    insert into alamat (id, jalan, kota, propinsi, kodepos) values ('2b','Jalan Komarudin','Cakung','Jakarta Timur',13940);
    insert into alamat (id, jalan, kota, propinsi, kodepos) values ('3b','Jalan Duku','Kebon Jeruk','Jakarta Pusat',57890);
    insert into alamat (id, jalan, kota, propinsi, kodepos) values ('4b','Jalan Kelapa','Jagakarsa','Jakarta Selatan',23956);
    insert into alamat (id, jalan, kota, propinsi, kodepos) values ('5b','Jalan Patriot','Bekasi Barat','Bekasi',47863);
    ```
- tambahkan `spring.jpa.hibernate.ddl-auto=create` di application.properties
- buat package `id.co.hanoman.training.webservices.dao` untuk interface dao
- buat dao interface AlamatDao.java
    ```java
    package id.co.hanoman.training.webservices.dao;

    import org.springframework.data.repository.PagingAndSortingRepository;

    import id.co.hanoman.training.webservices.entity.Alamat;

    public interface AlamatDao extends PagingAndSortingRepository<Alamat, String>{
        
    }
    ```
    Alamat refer ke entity Alamat.java sedang string refer ke type id dari entity yaitu String (interface = java yang tidak punya methode konkrit, jadi methode nya abstrak)
- add depedencies `compile('org.springframework.boot:spring-boot-starter-web')` untuk aplikasi web pada springboot
- run `gradle bootRun` untuk menjalankan web service
- buat package `id.co.hanoman.training.webservices.controller` sebagai folder controller untuk MVC di Java dan buat class AlamatController
    ```java
    package id.co.hanoman.training.webservices.controller;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RestController;

    import id.co.hanoman.training.webservices.dao.AlamatDao;
    import id.co.hanoman.training.webservices.entity.Alamat;

    @RestController
    @RequestMapping("/api")

    public class AlamatController {

        @Autowired
        private AlamatDao ad;
        
        @RequestMapping(value="/alamat", method=RequestMethod.GET)	
        public Page<Alamat> daftarAlamat(Pageable page){
            return ad.findAll(page);
        }
    }
    ```
- add line spring.jackson.serialization.indent_output=true untuk format data tampilan di web agar rapih
- add spring.jpa.show-sql=true to application.properties untuk memunculkan sql di console
- add line spring.jpa.properties.hibernate.format_sql=true di application.properties untuk format sql pada console
- API get alamat sudah bisa dijalankan dengan `http://localhost:8080/api/alamat/`
- create rest untuk mendapatkan record berdasarkan id pada table database tambahkan script berikut ke AlamatController.java
    ```java
    @RequestMapping(value="/alamat/{id}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Alamat> cariAlamatById(@PathVariable("id") String id){
		Alamat hasil = ad.findOne(id);
		if(hasil==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(hasil, HttpStatus.OK);
	}
    ```
- API get alamat by id sudah bisa dijalankan dengan `http://localhost:8080/api/alamat/{id}`
- create Rest untuk POST table database tambahkan script berikut ke AlamatController.java
    ```java
	@RequestMapping(value="/alamat", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void insertAlamatBaru(@RequestBody @Valid Alamat a){
		ad.save(a);
	}
    ```
- API add alamat sudah bisa dijalankan dengan `http://localhost:8080/api/alamat/` dengan Content-Type:application/json dan contoh data sebagai di bawah
    ```json
    {
 		"jalan": "Jalan Duren Runtuh",
 		"kota": "Duren Kuning",
 		"propinsi": "Buah Duren",
 		"kodepos": 88888
 	}
    ```
- create Rest untuk PUT (update data) table database tambahkan script berikut ke AlamatController.java
    ```java
    @RequestMapping(value="/alamat/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateAlamat(@PathVariable("id") String id, @RequestBody @Valid Alamat a){
        a.setId(id);
        ad.save(a);
    }
    ```
- API update alamat sudah bisa dijalankan dengan `http://localhost:8080/api/alamat/{id}` dengan Content-Type:application/json dan contoh data sebagai di bawah
    ```json
    {
 		"jalan" : "Jalan Duku Tuh",
 		"kota" : "Kebon Jeruk Tuh",
 		"propinsi" : "Jakarta Pusat Tuh",
 		"kodepos" : 57897
 	}
    ```
- create Rest untuk DELETE table database tambahkan script berikut ke AlamatController.java
    ```java
    @RequestMapping(value="/alamat/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void hapusAlamat(@PathVariable("id") String id){
        ad.delete(id);
    }
    ```
- API delete alamat sudah bisa dijalankan dengan `http://localhost:8080/api/alamat/{id}`





