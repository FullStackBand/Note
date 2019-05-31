#使用 POJO 对象绑定请求参数值
* Spring MVC 会按• 请求参数名和 POJO 属性名进行自动匹配，自动为该对象填充属性值。支持级联属性。
如：dept.deptId、dept.address.tel 等
* java代码
	* controller
```
	/**
	 * Spring MVC 会按请求参数名和 POJO 属性名进行自动匹配， 自动为该对象填充属性值。支持级联属性。
	 * 如：dept.deptId、dept.address.tel 等
	 */
	@RequestMapping("/testPojo")
	public String testPojo(User user) {
		System.out.println("testPojo: " + user);
		return SUCCESS;
	}
```
#### pojo
* User
```
public class User {
	
	private Integer id;
	
	private String username;
	private String password;

	private String email;
	private int age;
	
	private Address address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
		
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", email=" + email + ", age=" + age + "]";
	}
	
	public User(String username, String password, String email, int age) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
	}

	public User(Integer id, String username, String password, String email,
			int age) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
	}

	public User() {}
}

```
 * Address
```
public class Address {

	private String province;
	private String city;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address [province=" + province + ", city=" + city + "]";
	}
	
}
```
* 前台代码
```
<form action="springmvc/testPojo" method="post">
		username: <input type="text" name="username"/>
		<br>
		password: <input type="password" name="password"/>
		<br>
		email: <input type="text" name="email"/>
		<br>
		age: <input type="text" name="age"/>
		<br>
		city: <input type="text" name="address.city"/>
		<br>
		province: <input type="text" name="address.province"/>
		<br>
		<input type="submit" value="Submit"/>
	</form>
```
