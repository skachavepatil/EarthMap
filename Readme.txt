Please extract "earthmap" folder and perform below steps:

1) Import the earthmap project in Eclipse

2) Build the project

3) Change the OAUTH credentials in FourSquareAPIController.java (will look the alternate way to handle this)

3) Run the EarthmapApplication.java

4) Import the "earthmap_FourSquare_API.postman_collection" in Postman

5) Perfrom the below operations on application. Please refer "APIExecutionSteps.docx" document with attached Postman screenshots for more information.

 - Get Location By Name:

	GET	http://localhost:8080/foursquare/search/pune

 - Get Categories

	GET	http://localhost:8080/foursquare/categories

- Create the user define list

	POST	http://localhost:8080/foursquare/createlist

- Create the places under userdefined list

	POST	http://localhost:8080/foursquare/placesunderlist

