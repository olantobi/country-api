# ASSIGNMENT

Create a RESTFUL API for performing CRUD operations on a Country resource
using Java Springboot.
After completion of your application, a client should be able to perform the
following api calls and get the expected results:

 **POST /signup
 
payload will contain the following fields :
1. first name
2. last name
3. Date of birth
4. email
5. username
6. password

 **POST /login

payload will contain the following fields :
1. username / email
2. password

Successful login should return API status code 200 success and jwt token
Return 401 status code for incorrect username or password.
The returned JWT token should be used to access all the API below.

 **POST /countries

Add a country

payload will contain the following fields :
1. name
2. continent

 **GET /countries

Return all the countries in the DB.

Response should contain the following fields
1. name
2. continent
3. created (Date)

**PUT /countries/id

Update a country

**DELETE /countries/id

Delete country.