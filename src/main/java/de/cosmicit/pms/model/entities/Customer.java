package de.cosmicit.pms.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tbl_customer")
public class Customer {

	public enum Gender {
		FEMALE,
		MALE;
		@JsonValue
		public String value() { return this.name().toLowerCase(); }
	}

	@Id @GeneratedValue(strategy = IDENTITY)
	@Column(name = "customer_id", unique = true, nullable = false)
	private Long id;

	@Column(name = "customer_username")
	private String userName;

	@Column(name = "customer_firstname")
	private String firstName;

	@Column(name = "customer_lastname")
	private String lastName;

	@Column(name = "customer_email")
	private String email;

	@Column(name = "customer_birthdate")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;

	@Column(name = "customer_gender")
	private Gender gender;

	@Column(name = "customer_street")
	private String street;

	@Column(name = "customer_housenumber")
	private String houseNumber;

	@Column(name = "customer_zipcode")
	private String zipcode;

	@Column(name = "customer_city")
	private String city;

	@Column(name = "customer_country")
	private String country;

	// TODO to discuss about database schema and decide foreign key relationships
//	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//	@JoinTable(name = "tbl_customerlinkservicecontract", joinColumns = {
//			@JoinColumn(name = "customerlinkservicecontract_linkcustomerid", nullable = false, updatable = false) }, inverseJoinColumns = {
//					@JoinColumn(name = "customerlinkservicecontract_linkservicecontractid", nullable = false, updatable = false) })
//	@JsonSerialize(using = CollectionSerializer.class)
//	@JsonDeserialize(using = CollectionDeserializer.class)
//	private Set<Servicecontract> serviceContracts = new HashSet<>();

	public Long getId() {
		return this.id;
	}

	public void setId(Long Id) {
		this.id = Id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

//	public Set<Servicecontract> getServicecontracts() {
//		return this.serviceContracts;
//	}
//
//	public void setServiceContracts(Set<ServiceContract> serviceContracts) {
//		this.serviceContracts.clear();
//		this.serviceContracts.addAll(serviceContracts);
//	}
//
//	public void addServiceContract(ServiceContract serviceContract) {
//		this.serviceContracts.add(serviceContract);
//	}

}
