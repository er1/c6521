first = ["Percy", "Linda", "Valery", "Brenda", "Carol", "Stephanie", "Lucy", "Amy", "Laura", "Tarryn", "Belinda", "Fran", "Pam", "Jan", "Marcia", "Gale", "Kelly", "Luann", "Marie", "Mary", "Betsy", "Cindy", "Debbie", "Kathy", "Connie", "Claudia", "Joni", "Sonja", "Tammy", "Beth", "Nancy", "Gail", "Emily", "Nellie", "Margaret", "Marilyn", "Tracy", "Rosemary", "Joanne", "Donna", "Susan", "Patty", "Claire", "Laurie", "Dawn", "Greta", "Helene", "Ethel", "Fanny", "Keena", "Eleanor", "Kaye", "Theresa", "Hilda", "Sybil", "Dina", "Chris", "Tiffany", "Petra", "Barbra"]
last = ["Madison", "Curtis", "Meyer", "Bauer", "Thumer", "Ford", "Chen", "Braun", "Holm", "Senff", "Harup", "Ferguson", "Hall", "Andrews", "Watson", "Carr", "Dennis", "Fraser", "Hoffman", "White", "Vasin", "Tobian", "Browning", "Harlan", "Moe", "Stouder", "Smith", "James", "Peterson", "Coleman", "Kramer", "Gossick", "Schaefer", "Hayes", "Robinson", "Curry", "Hamilton", "Heiss", "Fleming", "Witt", "Gros", "Henderson", "Hendrix", "Owen", "Burton", "Robertson", "Landers", "Lancing", "Patterson", "Philips", "Thomas", "Weston", "Welch", "Rice", "Reynolds", "Quinn", "Park", "Hall", "Hess", "Healy"]
street = ["Sunrise Rd", "Stonehedge Blvd", "Midland St", "Tulip St", "Beley Rd", "Bryant Blvd", "Hamlet St", "Willow Rd", "Limetree Ln", "Genesse Blvd", "West Street Terr", "Spring County Blvd", "North Hampton St", "Erming Ln", "Ellis Terr", "Lincoln Rd", "Ashland St", "Old Pinbrick Dr", "Maple Ln", "Orrand Dr", "Bourg St", "Freeland Ave", "Cooper St", "Flanty Terr", "Rider Blvd", "Kennel Ln", "Potter Rd", "Brighton St", "East Parson St", "Dorwin Rd", "Tellfly St", "Wommert Ln", "Orange West", "Cedarwood Ln", "Main St", "First St", "Second St", "Third St", "Southern Dr", "Henly Dr", "Cimenny Rd", "Shalton Dr", "Freeton Blvd", "Brandy Run", "Plinfate St", "Darly Rd", "Buncaneer Dr", "Columbus Dr", "Lowel Rd", "Tomkins Blcd", "Manchester St", "New First Rd", "Burnet Dr", "Pleasant View Dr", "Fairfield Rd", "Anton Dr", "Sharon Rd", "Lake Dr", "Plymth Terr", "Lemoyer Blvd"]
city = ["Phoenix, AZ", "Arlington, TX", "Dayton, OH", "Wichita, KS", "Ames, IA", "Appleton, WI", "Detroit, MI", "Passaic, NJ", "Albany, NY", "Akron, OH", "Erie, PA", "Gary, IN", "Roanoke, VI", "Kinston, NC", "Hampton, VI", "Milwaukee, WI", "Richmond, VA", "Hamilton, OH", "Mentor, OH", "Massillon, OH", "Dallas, TX", "Garland, TX", "Galveston, TX", "Irving, TX", "Providence, RI", "Memphis, TN", "Knoxville, TN", "Vancouver, WA", "Seattle, WA", "Spokane, WA", "Beloit, WI", "Austin, TX", "Orange, NJ", "Plainfield, NJ", "Binghamton, NY", "Auburn, NY", "Yonkers, NY", "Biloxi, MS", "Berkeley, CA", "Passadena, CA", "Sunnyvale, CA", "Alton, IL", "Denver, CO", "Clearwater, FL", "Miami, FL", "Orlando, FL", "Albany, GA", "Athens, GA", "Rome, GA", "Addison, IL", "Aurora, IL", "Chicago, IL", "Emporia, KS", "Anderson, IN", "Indianapolis, IN", "Utica, NY", "Minneapolis, MN", "Omaha, NE", "Burlington, NC", "Raleigh, NC"]


for (i = 0; i < 1001; i++) {
	for (j = 0; j < 40; j++) {
		f = first[(Math.random() * first.length)|0]
		l = last[(Math.random() * last.length)|0]
		s = street[(Math.random() * street.length)|0]
		c = city[(Math.random() * city.length)|0]

		r = "";
		pad = Array(100).join(" ");
		
		r += ((Math.random() * 10000000 | 0) + pad).substr(0, 7);
		r += (f + pad).substr(0, 15);
		r += (l + pad).substr(0, 15);
		r += ((Math.random() * 1000000000 | 0) + pad).substr(0, 9);
		r += ((Math.random() * 100000 | 0) + " " + s + pad).substr(0, 54);

		console.log(r);

	}
	console.log(Array(97).join(" "));
}
