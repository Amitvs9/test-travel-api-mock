# Application springboot jpa 

#Resource endpoints:
 • Retrieve a list of airports:
 
	 • http://localhost:9090/airports

Query params:

   size: the size of the result
   page: the page to be selected in the paged response
   lang: the language, supported ones are nl and en
   http://localhost:9090/search/code?term=HYDterm: A search term that searches through code, name and description.

Example : http://localhost:9090/airports?term=HYD         [get Airport details for code HYD]
 
# Search a specific airport:

	• http://localhost:9090/search/code

Query params:

term: A search term that searches through code, name and description.


Example : http://localhost:9090/search/code?term=HYD   [get Airport details for code HYD]

# Retrieve a fare details:
 • Retrieve a fare details based on Source and destination
	
	• http://localhost:9090/fares/{source}/{destination}

Query params

 • currency: the requested resulting currency, supported ones are EUR and USD

 Example : http://localhost:9090/fares/ams/jfk            [get fare details between ams and Jfk]


# Statics[metrics] details:
 • Retrieve a Statics details
	
	• http://localhost:9090/rest/metrics

